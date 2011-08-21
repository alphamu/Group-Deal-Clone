package com.groupdealclone.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.groupdealclone.app.domain.ShoppingCart;

@Repository
public class JdbcShoppingCartDao implements ShoppingCartDao {

	@PersistenceUnit(unitName = "dbcon")
	EntityManagerFactory emf;

	@PersistenceContext
	EntityManager em;
	
	@Override
	public ShoppingCart getShoppingCart(String username) {
		try {
			ShoppingCart cart = em
					.createQuery("from ShoppingCart where username = ?1", ShoppingCart.class)
					.setParameter(1, username).getSingleResult();
			return cart;
		} catch (NoResultException noResult) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void update(ShoppingCart cart) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(cart);
		em.getTransaction().commit();
	}



}
