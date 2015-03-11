package com.Transpot.Fast.Repositorio;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import com.Transpot.Fast.model.Solicitacao;
import com.Transpot.Fast.util.cdi.jpa.Transactional;

public class RepositorioSolicitacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public void CadastrarSolicitacao(Solicitacao solicitacao){
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		manager.merge(solicitacao);
		trx.commit();
		
	}
	
	
	public Solicitacao porUsuario(String usuario_id) {
		try {
			return manager
					.createQuery("from Motorista where upper(usuario_id) = :usuario_id",
							Solicitacao.class)
					.setParameter("usuario_id", usuario_id.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	

	public List<Solicitacao> todos() {
		try {
			return manager
					.createQuery("from Solicitacao ",
							Solicitacao.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	
	public Solicitacao porId(Long id) {
		return manager.find(Solicitacao.class, id);
	}
	


	@Transactional
	public void remover(Solicitacao usuario) {

		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		try {
		
		
		usuario = porId(usuario.getId());
		manager.remove(usuario);
		trx.commit();
		manager.flush();

		
		} catch (Exception e) {
			
		}
		
	}
	
	

}
