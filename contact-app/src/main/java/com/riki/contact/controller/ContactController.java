package com.riki.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riki.contact.form.ContactForm;
import com.riki.contact.form.annotation.OnCreate;
import com.riki.contact.form.annotation.OnUpdate;
import com.riki.contact.model.Contact;
import com.riki.contact.model.pagination.Paging;
import com.riki.contact.service.ContactService;

@Controller
@Validated
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/")
	public String findContactList(
			@RequestParam(value = "page", required = true, defaultValue = "0") Integer page,
			@RequestParam(value = "limit", required = true, defaultValue = "2") Integer limit,
			@RequestParam(value = "sortBy", required = true, defaultValue = "id") String sortBy,
			@RequestParam(value = "direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(value = "search", required = false) String search,
			Model model) {
		Paging<Contact> contacts = contactService.findContactAll(page, limit, sortBy, direction, search);
		model.addAttribute("contacts", contacts); 
		return "contact/contact-list";
	}
	
	@GetMapping("/contact/new")
	public String loadContactForm(Model model) {
		model.addAttribute("contactForm", new ContactForm());
		return "contact/contact-create";
	}
	
	@PostMapping("/contact/new")
	public String addNewContact(@ModelAttribute("contactForm") @Validated(OnCreate.class) ContactForm contactForm,
			BindingResult bindingResult,
			Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("contactForm", contactForm);
			return "contact/contact-create";
		}
		contactService.createNewContact(contactForm);
		return "redirect:/";
	}
	
	@GetMapping("/contact/update/{id}")
	public String loadUpdateContactForm(@PathVariable("id") Long id, Model model) {
		ContactForm contactForm = contactService.findContact(id);
		model.addAttribute("contactForm", contactForm);
		return "contact/contact-update";
	}
	
	@PostMapping("/contact/update/{id}")
	public String updateContact(@PathVariable("id") Long id, 
			@ModelAttribute("contactForm") @Validated(OnUpdate.class) ContactForm contactForm, 
			BindingResult bindingResult,
			Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("contactForm", contactForm);
			return "contact/contact-update";
		}
		contactService.updateContact(id, contactForm);
		return "redirect:/";
	}
	
	@GetMapping("/contact/delete/{id}")
	public String deleteContact(@PathVariable("id") Long id, Model model) {
		contactService.deleteContact(id);
		return "redirect:/";
	}
	
}


		
		
		
		
		