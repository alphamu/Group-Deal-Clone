package com.groupdealclone.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.domain.Account;

@Repository
@Transactional
public class JdbcAccountDao implements AccountDao {

	@PersistenceUnit(unitName = "dbcon")
	EntityManagerFactory emf;
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Account getUser(String username) {
		try {
			Account account = em
					.createQuery("from Account where username = ?1", Account.class)
					.setParameter(1, username).getSingleResult();
			return account;
		} catch (NoResultException noResult) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public void updateUser(Account account){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();
	}
	
	@Override
	public void saveUser(Account account){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
	}
	


}
