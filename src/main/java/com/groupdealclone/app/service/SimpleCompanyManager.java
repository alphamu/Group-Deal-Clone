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

	public Company getCompany(Long id) {
		return companyDao.getCompany(id);
	}

	@Transactional
	public void saveCompany(Company company) {
		companyDao.saveCompany(company);
		
	}

	public List<Company> getCompany() {
		return companyDao.getCompanies();
	}

	@Override
	public void updateCompany(Company company) {
		companyDao.updateCompany(company);
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

}
