package com.Transpot.Fast.Repositorio;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.Transpot.Fast.model.Instituicao;
import com.Transpot.Fast.util.cdi.jpa.Transactional;

public class RepositorioInstituicao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public void CadastrarInstituicao(Instituicao instituicao) {

		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		manager.merge(instituicao);
		trx.commit();

	}

	@SuppressWarnings("unchecked")
	public List<Instituicao> filtrados(Instituicao instituicao) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Instituicao.class);

		if (StringUtils.isNotBlank(instituicao.getNome())) {
			criteria.add(Restrictions.ilike("nome", instituicao.getNome(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();

	}
	
	
	public List<Instituicao> todas() {
		
	return	 manager.createQuery("from Instituicao ",	Instituicao.class).getResultList();
		
	}

	public Instituicao porId(Long id) {
		return manager.find(Instituicao.class, id);
	}

	@Transactional
	public void remover(Instituicao instituicao) {

		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		try {
		
		
		instituicao = porId(instituicao.getId());
		manager.remove(instituicao);
		trx.commit();
		manager.flush();

		
		} catch (Exception e) {
			
		}
		
	}

}
