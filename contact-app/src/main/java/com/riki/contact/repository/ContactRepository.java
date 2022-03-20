package com.riki.contact.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.riki.contact.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
	Boolean existsByEmailAndActiveTrue(String email);
	public Optional<Contact> findByIdAndActiveTrue(Long id);
	
	@Query("SELECT c FROM Contact c WHERE (c.active = true) and ((:fullname is null or c.fullname LIKE :fullname) or (:email is null or c.email LIKE :email) or (:phoneNumber is null or c.phoneNumber LIKE :phoneNumber))") 
	public Page<Contact> search(@Param("fullname") String fullname, @Param("email") String email, @Param("phoneNumber") String phoneNumber, Pageable pageable);
	
}
