package com.Transpot.Fast.Repositorio;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.Transpot.Fast.model.Veiculo;

public class RepositorioVeiculo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public Veiculo porId(Long id) {
		return manager.find(Veiculo.class, id);
	}

}
