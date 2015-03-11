package com.Transpot.Fast.Repositorio;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.Transpot.Fast.model.Contrato;
import com.Transpot.Fast.util.cdi.jpa.Transactional;

public class RepositorioContrato implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public void CadastrarSolicitacao(Contrato contrato){
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		manager.merge(contrato);
		trx.commit();
		
	}
	
	
	public Contrato porUsuario(String usuario_id) {
		try {
			return manager
					.createQuery("from Contrato where upper(usuario_id) = :usuario_id",
							Contrato.class)
					.setParameter("usuario_id", usuario_id.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Contrato> porMotorista(String motorista_id) {
		try {
			return (List<Contrato>) manager
					.createQuery("from Contrato where upper(motorista_id) = :motorista_id",
							Contrato.class)
					.setParameter("motorista_id", motorista_id.toUpperCase())
					.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Contrato> todos() {
		try {
			return manager
					.createQuery("from Contrato ",
							Contrato.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	
	public Contrato porId(Long id) {
		return manager.find(Contrato.class, id);
	}
	


	@Transactional
	public void remover(Contrato contrato) {

		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		try {
		
		
		contrato = porId(contrato.getId());
		manager.remove(contrato);
		trx.commit();
		manager.flush();

		
		} catch (Exception e) {
			
		}
		
	}
	
	

}
