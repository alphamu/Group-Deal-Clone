package com.groupdealclone.app.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.groupdealclone.app.domain.Account;

@Configuration
public class EditUserValidator implements Validator {
	
	private @Value("#{config.minPasswordSize}") int minPasswordLength;

	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Account a = (Account) target;
		errors = validatePassword(a, errors);
	}

	public Errors validatePassword(Account a, Errors errors) {
		String password = a.getPassword();
		String confirmPassword = a.getConfirmPassword();
		if (!((password == null || password.length() == 0) && (confirmPassword == null || confirmPassword.length() == 0))) {
			if (password == null || confirmPassword == null || (!(password.equals(confirmPassword)))) {
				errors.rejectValue("password", "password.mismatch", "Passwords did not match.");
			} else if (password.length() < minPasswordLength) {
				errors.rejectValue("password", "password.length", "Password too short.");
			}
		} 
		return errors;
	}
}
