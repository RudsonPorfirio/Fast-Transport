package com.Transpot.Fast.Repositorio;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.Transpot.Fast.model.Endereco;

public class RepositorioEndereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public Endereco porId(Long id) {
		return manager.find(Endereco.class, id);
	}

}
