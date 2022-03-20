package com.riki.contact.form.annotation;

import java.lang.annotation.*;
import javax.validation.*;

import com.riki.contact.form.validation.UniqueEmailValidator;


@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueEmail {
	
	public String message() default "This email is already in use";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default{};

}
