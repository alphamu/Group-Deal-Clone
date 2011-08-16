package com.groupdealclone.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.domain.Account;

@Repository
@Transactional
public class JdbcAccountDao implements AccountDao {

	@PersistenceContext
	EntityManager em;

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
	


}
