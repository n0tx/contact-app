package com.riki.contact.service;

import com.riki.contact.form.ContactForm;
import com.riki.contact.model.Contact;
import com.riki.contact.model.pagination.Paging;

public interface ContactService {

	public Paging<Contact> findContactAll(Integer page, Integer limit, String sortBy, String direction, String search);
	
	public void createNewContact(ContactForm contactForm);
	
	public ContactForm findContact(Long id);

	public void updateContact(Long id, ContactForm contactForm);

	public void deleteContact(Long id);
	
	public Boolean isEmailAlreadyInUse(String email);
	
}
