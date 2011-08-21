package com.groupdealclone.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
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
	PasswordEncoder	passwordEncoder;
	
	@Autowired
	AccountDao accountDao;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		Account account = accountDao.getUser(username.toLowerCase());
		if (account == null)
			throw new UsernameNotFoundException("No account found for '"
					+ username.toLowerCase() + "'");
		
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
		String lowerCaseUsername = account.getUsername().toLowerCase();
		account.setUsername(lowerCaseUsername);
		accountDao.updateUser(account);
	}
	
	/***
	 * Only to be used when creating a new account
	 * @param account
	 */
	@Transactional
	public void saveAccount(Account account){
		String lowerCaseUsername = account.getUsername().toLowerCase();
		account.setUsername(lowerCaseUsername);
		long time = System.currentTimeMillis();
		account.setMemberSince(new java.sql.Date(time));
		String timeAsSalt = String.valueOf(time);
		String code = passwordEncoder.encodePassword(account.getUsername(), timeAsSalt);
		account.setActivationCode(code);
		account.setEnabled(false); //should become true on activation.
		accountDao.saveUser(account);
	}
	
	
}
