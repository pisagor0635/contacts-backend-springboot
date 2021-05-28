package com.ab.contactsbackend.controller;

import com.ab.contactsbackend.model.Contact;
import com.ab.contactsbackend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    //get all contacts
    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    //create contact rest api
    @PostMapping("/contacts")
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

}
