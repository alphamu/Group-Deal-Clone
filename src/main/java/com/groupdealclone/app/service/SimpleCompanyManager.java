package com.groupdealclone.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.dao.CompanyDao;
import com.groupdealclone.app.domain.Company;

@Service
public class SimpleCompanyManager implements CompanyManager {
	
	@Autowired 
	private CompanyDao companyDao;

	@Override
	public Company getCompany(Long id) {
		return companyDao.getCompany(id);
	}

	@Override
	@Transactional
	public void saveCompany(Company company) {
		String name = company.getName().trim().toLowerCase();
		company.setName(name);
		companyDao.saveCompany(company);
		
	}

	@Override
	public List<Company> getCompany() {
		return companyDao.getCompanies();
	}

	@Override
	public void updateCompany(Company company) {
		String name = company.getName().trim().toLowerCase();
		company.setName(name);
		companyDao.updateCompany(company);
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	public List<Company> getCompanies(String nameLike) {
		return companyDao.getCompanies("%"+nameLike.trim().toLowerCase()+"%");
	}

}
