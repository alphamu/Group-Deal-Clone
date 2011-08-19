package com.groupdealclone.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupdealclone.app.dao.AccountDao;
import com.groupdealclone.app.domain.Account;

@Service("userDetailsService") 
public class AccountService implements UserDetailsService {
	
	@Autowired
	AccountDao accountDao;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		Account account = accountDao.getUser(username);
		if (account == null)
			throw new UsernameNotFoundException("No account found for '"
					+ username + "'");
		
		return account;
	}
	public UserDetails loadUserById(Long id)
			throws UsernameNotFoundException, DataAccessException {
		
		Account account = accountDao.getUser(id);
		if (account == null)
			throw new UsernameNotFoundException("No account found");

		return account;
	}

	@SuppressWarnings("unused")
	private GrantedAuthority[] getAuthorities(boolean isAdmin) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		if (isAdmin) {
			authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}
		return authList.toArray(new GrantedAuthority[] {});
	}
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	@Transactional
	public void updateAccount(Account account){
		accountDao.updateUser(account);
	}
	
	@Transactional
	public void saveAccount(Account account){
		accountDao.saveUser(account);
	}
	
	
}
