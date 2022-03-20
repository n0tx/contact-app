package com.riki.contact.form;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.riki.contact.form.annotation.OnCreate;
import com.riki.contact.form.annotation.OnUpdate;
import com.riki.contact.form.annotation.PatternPhoneNumber;
import com.riki.contact.form.annotation.UniqueEmail;

public class ContactForm {
	
	private Long id;
	
	@NotBlank(message = "name must not blank", groups = {OnCreate.class, OnUpdate.class})
	@Size(max = 31, message = "name size too long, max 30 letters", groups = {OnCreate.class, OnUpdate.class})
	private String fullname;
	
	@NotNull(message = "birth date must not blank", groups = {OnCreate.class, OnUpdate.class})
	@PastOrPresent(message = "birth date must not exceed current date", groups = {OnCreate.class, OnUpdate.class})
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthDate;
	
	@NotBlank(message = "email must not blank", groups = {OnCreate.class, OnUpdate.class})
	@Email(message = "email must be a valid email address", groups = {OnCreate.class, OnUpdate.class})
	@UniqueEmail(groups = {OnCreate.class})
	private String email;
	
	@PatternPhoneNumber(groups = {OnCreate.class, OnUpdate.class})
	private String phoneNumber;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}
