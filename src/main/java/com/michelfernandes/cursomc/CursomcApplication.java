package com.michelfernandes.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.michelfernandes.cursomc.domain.Categoria;
import com.michelfernandes.cursomc.domain.Cidade;
import com.michelfernandes.cursomc.domain.Cliente;
import com.michelfernandes.cursomc.domain.Endereco;
import com.michelfernandes.cursomc.domain.Estado;
import com.michelfernandes.cursomc.domain.ItemPedido;
import com.michelfernandes.cursomc.domain.Pagamento;
import com.michelfernandes.cursomc.domain.PagamentoComBoleto;
import com.michelfernandes.cursomc.domain.PagamentoComCartao;
import com.michelfernandes.cursomc.domain.Pedido;
import com.michelfernandes.cursomc.domain.Produto;
import com.michelfernandes.cursomc.domain.enums.EstadoPagamento;
import com.michelfernandes.cursomc.domain.enums.TipoCliente;
import com.michelfernandes.cursomc.repositories.CategoriaRepository;
import com.michelfernandes.cursomc.repositories.CidadeRepository;
import com.michelfernandes.cursomc.repositories.ClienteRepository;
import com.michelfernandes.cursomc.repositories.EnderecoRepository;
import com.michelfernandes.cursomc.repositories.EstadoRepository;
import com.michelfernandes.cursomc.repositories.ItemPedidoRepository;
import com.michelfernandes.cursomc.repositories.PagamentoRepository;
import com.michelfernandes.cursomc.repositories.PedidoRepository;
import com.michelfernandes.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
    // Command... inicia um metodo quando a aplicação inicia
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia",est1);
		Cidade c2 = new Cidade(null, "São Vicente",est2);
		Cidade c3 = new Cidade(null, "Santos",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		Cliente  cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "34864830828", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("13983408077", "88728728782"));
		
		Endereco e1 = new  Endereco(null, "Rua Alcides alves","300","apt 500","Jardim","11330757",cli1, c1);
		Endereco e2 = new  Endereco(null, "Rua. Praça republic","87","sal 44","Centro","11330757",cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
	    Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 19:35"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
		
	}
	

	
}