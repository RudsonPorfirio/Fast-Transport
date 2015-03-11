package com.Transpot.Fast.Repositorio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.Transpot.Fast.model.Motorista;
import com.Transpot.Fast.model.StatusMotorista;

public class RepositorioMotorista implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public void CadastrarMotorista(Motorista motorista) {

		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		manager.merge(motorista);
		trx.commit();

	}

	@SuppressWarnings("unchecked")
	public List<Motorista> filtrados(Motorista motorista) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Motorista.class);

		if (StringUtils.isNotBlank(motorista.getCpf())) {
			criteria.add(Restrictions.ilike("cpf", motorista.getCpf()));
		}

		if (StringUtils.isNotBlank(motorista.getNome())) {
			criteria.add(Restrictions.ilike("nome", motorista.getNome(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();

	}
	
	public List<Motorista> ativo() {
		
		List<Motorista> listaux =  new 	ArrayList<Motorista>();
		
		Motorista m = new Motorista();
		m.setStatusMotorista(StatusMotorista.ATIVO);
		
		try {
			
			List<Motorista> lista = manager
					.createQuery("from Motorista ",
							Motorista.class).getResultList();
			
			
			for (Motorista motorista : lista) {
					
					if (motorista.getStatusMotorista().toString().equalsIgnoreCase("ATIVO")) {
						
						listaux.add(motorista);
						
					}
				
			}
			
		} catch (NoResultException e) {
			return null;
		}
		
		return listaux;
	}
	
	
	public Motorista porId(Long id) {
		return manager.find(Motorista.class, id);
	}
	
	public List<Motorista> todos() {
		try {
			return manager
					.createQuery("from Motorista ",
							Motorista.class).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Motorista porEmail(String email) {
		try {
			return manager
					.createQuery("from Motorista where upper(email) = :email",
							Motorista.class)
					.setParameter("email", email.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Motorista porCpf(String cpf) {
		try {
			return manager
					.createQuery("from Motorista where upper(cpf) = :cpf",
							Motorista.class)
					.setParameter("cpf", cpf.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void remover(Motorista motoristaSelecionada) {
		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		try {

			motoristaSelecionada = porId(motoristaSelecionada.getId());
			manager.remove(motoristaSelecionada);
			trx.commit();
			manager.flush();

		} catch (Exception e) {

		}

	}

}
