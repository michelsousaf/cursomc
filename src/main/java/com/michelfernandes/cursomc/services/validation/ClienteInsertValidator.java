package com.michelfernandes.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.michelfernandes.cursomc.domain.Cliente;
import com.michelfernandes.cursomc.domain.enums.TipoCliente;
import com.michelfernandes.cursomc.dto.ClienteNewDTO;
import com.michelfernandes.cursomc.repositories.ClienteRepository;
import com.michelfernandes.cursomc.resources.exception.FieldMessage;
import com.michelfernandes.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {



	@Autowired

	private ClienteRepository repo;

	

	@Override

	public void initialize(ClienteInsert ann) {

	}



	@Override

	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		

		List<FieldMessage> list = new ArrayList<>();
		
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {

			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));

		}



		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {

			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));

		}

		
		for (FieldMessage e : list) {

			context.disableDefaultConstraintViolation();

			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())

					.addConstraintViolation();

		}

		return list.isEmpty();

	}

}