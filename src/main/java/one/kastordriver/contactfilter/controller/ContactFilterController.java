package one.kastordriver.contactfilter.controller;

import one.kastordriver.contactfilter.domain.Contact;
import one.kastordriver.contactfilter.domain.Contacts;
import one.kastordriver.contactfilter.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/hello")
public class ContactFilterController {

    @Autowired
    ContactRepository contactRepository;

    @RequestMapping("/filter")
    public ResponseEntity<Contacts> filter(@RequestParam(value = "filterName", required = true) String filterName) {
        List<Contact> contacts = new ArrayList<>();
        contactRepository.findAll().forEach(contact -> contacts.add(contact));
        return ResponseEntity.ok().body(new Contacts(contacts));
    }
}
