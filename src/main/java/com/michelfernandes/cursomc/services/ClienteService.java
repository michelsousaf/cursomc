package com.michelfernandes.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelfernandes.cursomc.domain.Cliente;
import com.michelfernandes.cursomc.repositories.ClienteRepository;
import com.michelfernandes.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! id:" + id
					+ ", Tipo:" + Cliente.class.getName());
		}
		return obj;
	}
}
