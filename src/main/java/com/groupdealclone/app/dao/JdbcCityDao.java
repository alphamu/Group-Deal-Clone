package com.groupdealclone.app.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.domain.City;

@Repository
@Transactional
public class JdbcCityDao implements CityDao {

	@PersistenceUnit(unitName = "dbcon")
	EntityManagerFactory emf;

	@PersistenceContext
	EntityManager em;

	@Override
	public List<City> getCities() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<City> criteria = builder.createQuery(City.class);
		Root<City> city = criteria.from(City.class);
		criteria.orderBy(builder.asc(city.get("name")));
		TypedQuery<City> query = em.createQuery(criteria);
		List<City> result = query.getResultList();

		return result;
	}

	@Override
	@Transactional
	public void saveCity(City city) {
		// begin transaction
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		if (!em.contains(city)) {
			// persist object - add to entity manager
			em.persist(city);
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
	public void updateCity(City city) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(city);
		em.getTransaction().commit();
	}
	
	@Override
	public City getCity(Long id){
		City city = em.find(City.class,id);
		return city;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCities(String... cityIn) {
		final String jpql = "from City where name in (:names)";
		Query q = em.createQuery(jpql,City.class);
		
		q.setParameter("names", Arrays.asList(cityIn));
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<City> getCities(String cityLike) {
		final String jpql = "from City where name LIKE :names";
		Query q = em.createQuery(jpql,City.class);
		
		q.setParameter("names", cityLike);

		return q.getResultList();
	}

}
