package com.riki.contact.form.validation;

import javax.validation.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.riki.contact.form.annotation.UniqueEmail;
import com.riki.contact.service.ContactService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private ContactService authorService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && !authorService.isEmailAlreadyInUse(value);
	}
	
	
}
	