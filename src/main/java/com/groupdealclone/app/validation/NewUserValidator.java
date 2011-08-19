package com.groupdealclone.app.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.groupdealclone.app.domain.Account;


public class NewUserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Account a = (Account)target;
		errors = validatePassword(a,errors);
	}
	
	public Errors validatePassword(Account a, Errors errors){
		String password = a.getPassword();
		String confirmPassword = a.getConfirmPassword();
		if(password == null || confirmPassword == null || (!(password.equals(confirmPassword)))) {
			errors.rejectValue("password", "password.mismatch", "Passwords did not match.");
		}
		else if(password.length() < 8) {
			errors.rejectValue("password", "password.length", "Password too short.");
		}
		return errors;
	}

}
