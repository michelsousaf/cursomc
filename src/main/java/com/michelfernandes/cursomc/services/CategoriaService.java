package com.michelfernandes.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelfernandes.cursomc.domain.Categoria;
import com.michelfernandes.cursomc.repositories.CategoriaRepository;
import com.michelfernandes.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! id:" + id
					+ ", Tipo:" + Categoria.class.getName());
		}
		return obj;
	}

	public Categoria insert(Categoria obj) {
		// se tiver null salva se não atualiza
		obj.setId(null);
		return repo.save(obj);
	}
}
