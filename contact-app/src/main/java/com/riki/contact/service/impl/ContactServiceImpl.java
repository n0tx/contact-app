package com.riki.contact.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.riki.contact.form.ContactForm;
import com.riki.contact.model.Contact;
import com.riki.contact.model.pagination.Paging;
import com.riki.contact.repository.ContactRepository;
import com.riki.contact.service.ContactService;
import com.riki.contact.util.PaginationUtil;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public Paging<Contact> findContactAll(Integer page, Integer limit, String sortBy,
			String direction, String search) {

		
		String byName = ObjectUtils.isEmpty(search) ? "%%" : "%" + search + "%";
		String byEmail = ObjectUtils.isEmpty(search) ? "%%" : "%" + search + "%";
		String byPhone = ObjectUtils.isEmpty(search) ? "%%" : "%" + search + "%";
		
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(page, limit, sort);
		
		Page<Contact>  pageResult = contactRepository.search(byName, byEmail, byPhone, pageable);
		
		List<Contact > contacts = pageResult.stream().map((p)-> {
			Contact contact =  new Contact();
			contact.setId(p.getId());
			contact.setFullname(p.getFullname());
			contact.setEmail(p.getEmail());
			contact.setBirthDate(p.getBirthDate());
			contact.setPhoneNumber(p.getPhoneNumber());
			return contact;
		}).collect(Collectors.toList());
		
		return PaginationUtil.createResultPagingDTO(contacts, pageResult.getTotalElements(), pageResult.getTotalPages(), page);
	}
	
	@Override
	public void createNewContact(ContactForm contactForm) {
		Contact contact = new Contact();
		contact.setFullname(contactForm.getFullname());
		contact.setBirthDate(contactForm.getBirthDate());
		contact.setEmail(contactForm.getEmail());
		contact.setPhoneNumber(contactForm.getPhoneNumber());
		contact.setActive(Boolean.TRUE);
		contactRepository.save(contact);
	}
	
	@Override
	public ContactForm findContact(Long id) {
		ContactForm contactForm = new ContactForm();
		Contact contact = contactRepository.findByIdAndActiveTrue(id).orElseThrow(()->new IllegalArgumentException("Invalid Contact Id: " + id));
		contactForm.setId(id);
		contactForm.setFullname(contact.getFullname());
		contactForm.setBirthDate(contact.getBirthDate());
		contactForm.setEmail(contact.getEmail());
		contactForm.setPhoneNumber(contact.getPhoneNumber());
		return contactForm;
	}
	
	@Override
	public void updateContact(Long id, ContactForm contactForm) {
		Contact contact = contactRepository.findByIdAndActiveTrue(id).orElseThrow(()->new IllegalArgumentException("Invalid Contact Id: " + id));
		contact.setFullname(contactForm.getFullname());
		contact.setBirthDate(contactForm.getBirthDate());
		contact.setEmail(contactForm.getEmail());
		contact.setPhoneNumber(contactForm.getPhoneNumber());
		contactRepository.save(contact);
	}
	
	@Override
	public void deleteContact(Long id) {
		Contact contact = contactRepository.findByIdAndActiveTrue(id).orElseThrow(()->new IllegalArgumentException("Invalid contact Id: " + id));
		contact.setActive(Boolean.FALSE);
		contactRepository.save(contact);
	}

	@Override
	public Boolean isEmailAlreadyInUse(String email) {
		return contactRepository.existsByEmailAndActiveTrue(email);
	}

	
}
