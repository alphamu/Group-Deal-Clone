package com.groupdealclone.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.domain.Category;

@Repository
public class JdbcCategoryDao implements CategoryDao {

	@PersistenceUnit(unitName = "dbcon")
	EntityManagerFactory	emf;

	@PersistenceContext
	EntityManager			em;

	@Override
	public List<Category> getCategories() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
		@SuppressWarnings("unused")
		Root<Category> city = criteria.from(Category.class);
		TypedQuery<Category> query = em.createQuery(criteria);
		List<Category> result = query.getResultList();

		return result;
	}

	@Override
	public Category getCategory(Long id) {
		Category cat = em.find(Category.class, id);
		return cat;
	}

	@Override
	@Transactional
	public void setCategory(Category category) {
		// begin transaction
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			if (!em.contains(category)) {
				// persist object - add to entity manager
				em.persist(category);
				// flush em - save to DB
				em.flush();
			}
			// commit transaction at all
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	@Transactional
	public void updateCategory(Category category) {
		// begin transaction
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			// persist object - add to entity manager
			em.merge(category);
			// flush em - save to DB
			em.flush();
			// commit transaction at all
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public Category getCategory(String name) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
		Root<Category> cat = criteria.from(Category.class);
		criteria.select(cat).where(builder.equal(cat.get("name"), name));
		TypedQuery<Category> query = em.createQuery(criteria);
		Category result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	@Override
	public List<Category> getCategories(String nameLike) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
		Root<Category> cat = criteria.from(Category.class);
		criteria.select(cat).where(builder.like(cat.<String>get("name"), nameLike));
		TypedQuery<Category> query = em.createQuery(criteria);
		List<Category> result = null;
		try {
			result = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return result;
	}

}
