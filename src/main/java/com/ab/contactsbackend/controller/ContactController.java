package com.ab.contactsbackend.controller;

import com.ab.contactsbackend.exception.ResourceNotFoundException;
import com.ab.contactsbackend.model.Contact;
import com.ab.contactsbackend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
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

    //get contact by id rest api
    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact>  getContactById(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id : " + id));
        return ResponseEntity.ok(contact);
    }

    //update contact rest api
    @PutMapping("/contacts/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contactUpdated) {

        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id : " + id));

        contact.setFirstName(contactUpdated.getFirstName());
        contact.setLastName(contactUpdated.getLastName());
        contact.setEmail(contactUpdated.getEmail());
        contact.setPhoneNumber(contactUpdated.getPhoneNumber());

        contact = contactRepository.save(contact);

        return ResponseEntity.ok(contact);

    }

    //delete contact rest api
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteContact(@PathVariable Long id){

        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id : " + id));

        contactRepository.delete(contact);
        Map<String ,Boolean> response = new HashMap<>();
        response.put("deleted",true);

        return ResponseEntity.ok(response);
    }

}
