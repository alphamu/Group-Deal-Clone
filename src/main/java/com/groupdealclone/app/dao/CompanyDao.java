package com.groupdealclone.app.dao;

import java.util.List;

import com.groupdealclone.app.domain.Company;

public interface CompanyDao {
	
	public List<Company> getCompanies();
	
	public List<Company> getCompanies(String nameLike);
	
	public List<Company> getAllCompanies();
	
	public Company getCompany(Long id);
	
	public Company getCompany(String name);
	
	public void saveCompany(Company company);
	
	public void updateCompany(Company company);
}
