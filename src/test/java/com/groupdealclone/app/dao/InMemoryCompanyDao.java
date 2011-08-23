package com.groupdealclone.app.dao;

import java.util.ArrayList;
import java.util.List;

import com.groupdealclone.app.domain.Company;

public class InMemoryCompanyDao implements CompanyDao {

	List<Company> companies;
	
	@Override
	public List<Company> getCompanies() {
		return companies;
	}

	@Override
	public Company getCompany(Long id) {
		for(Company c: companies){
			if(c.getId().equals(id)){
				return c;
			}
		}
		return null;
	}

	@Override
	public void saveCompany(Company company) {
		if(companies == null)
			companies = new ArrayList<Company>();
		companies.add(company);
	}

	@Override
	public void updateCompany(Company company) {
		if(companies == null)
			companies = new ArrayList<Company>();
		if(companies.contains(company)) {
			companies.remove(company);
			companies.add(company);
		}
	}

	@Override
	public Company getCompany(String name) {
		for(Company c: companies){
			if(c.getName().equals(name)){
				return c;
			}
		}
		return null;
	}

}
