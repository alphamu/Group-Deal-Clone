package com.groupdealclone.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.domain.Company;

@Transactional
public class JdbcCompanyDao implements CompanyDao {

	@PersistenceUnit(unitName = "dbcon")
	EntityManagerFactory emf;

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Company> getCompanies() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
		@SuppressWarnings("unused")
		Root<Company> company = criteria.from(Company.class);
		TypedQuery<Company> query = em.createQuery(criteria);
		List<Company> result = query.getResultList();

		return result;
	}

	@Override
	@Transactional
	public void saveCompany(Company company) {
		// begin transaction
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		if (!em.contains(company)) {
			// persist object - add to entity manager
			em.persist(company);
			// flush em - save to DB
			em.flush();
		}
		// commit transaction at all
		em.getTransaction().commit();
	}

	EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	@Transactional
	public void updateCompany(Company company) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(company);
		em.getTransaction().commit();
	}
	
	@Override
	public Company getCompany(Long id){
		Company company = em.find(Company.class,id);
		return company;
	}

}
