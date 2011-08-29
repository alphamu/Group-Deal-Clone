package com.groupdealclone.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.domain.Company;

@Repository
@Transactional
public class JdbcCompanyDao implements CompanyDao {

	@PersistenceUnit(unitName = "dbcon")
	EntityManagerFactory	emf;

	@PersistenceContext
	EntityManager			em;

	@Override
	public List<Company> getAllCompanies() {
		String query = "FROM Company company ORDER BY name";
		List<Company> co = em.createQuery(query, Company.class).getResultList();
		return co;
	}

	@Override
	public List<Company> getCompanies() {
		String query = "SELECT DISTINCT company.* FROM Company company INNER JOIN Campaign camp ON company.id=camp.company_id WHERE ?1 BETWEEN camp.startDate AND camp.endDate ORDER BY company.name";
		Query q = em.createNativeQuery(query, Company.class);
		q.setParameter(1, new java.util.Date(), TemporalType.DATE);
		@SuppressWarnings("unchecked")
		List<Company> co = q.getResultList();
		return co;
	}
	
	public List<Company> getCompanies(String nameLike) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
		Root<Company> co = criteria.from(Company.class);
		criteria.select(co).where(builder.like(co.<String>get("name"), nameLike));
		TypedQuery<Company> query = em.createQuery(criteria);
		List<Company> result = null;
		try {
			result = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	@Override
	@Transactional
	public void saveCompany(Company company) {
		// begin transaction
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			if (!em.contains(company)) {
				// persist object - add to entity manager
				em.persist(company);
				// flush em - save to DB
				em.flush();
			}
			// commit transaction at all
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	@Transactional
	public void updateCompany(Company company) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(company);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public Company getCompany(Long id) {
		Company company = em.find(Company.class, id);
		return company;
	}

	@Override
	public Company getCompany(String name) {
		try {
			Company co = em.createQuery("from Company where name = ?1", Company.class).setParameter(1, name.trim().toUpperCase()).getSingleResult();
			return co;
		} catch (NoResultException noResult) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
