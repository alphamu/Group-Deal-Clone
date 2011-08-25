package com.groupdealclone.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.groupdealclone.app.domain.Company;

@Service
public interface CompanyManager {
	
	public Company getCompany(Long id);
	
	public List<Company> getCompany();
	
	public List<Company> getCompanies(String nameLike);
	
	public void saveCompany(Company co);
	
	public void updateCompany(Company co);

}
