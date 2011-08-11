package com.groupdealclone.app.dao;

import java.util.List;

import com.groupdealclone.app.domain.Company;

public interface CompanyDao {
	public List<Company> getCompanies();
	
	public Company getCompany(Long id);
	
	public void saveCompany(Company company);
	
	public void updateCompany(Company company);
}
