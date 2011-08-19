package com.groupdealclone.app.dao;

import com.groupdealclone.app.domain.Account;

public interface AccountDao {

	public Account getUser(String username);
	
	public Account getUser(Long id);
	
	public void updateUser(Account account);
	
	public void saveUser(Account account);
}
