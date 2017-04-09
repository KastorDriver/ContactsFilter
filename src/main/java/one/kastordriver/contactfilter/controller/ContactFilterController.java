package one.kastordriver.contactfilter.controller;

import one.kastordriver.contactfilter.domain.Contact;
import one.kastordriver.contactfilter.domain.Contacts;
import one.kastordriver.contactfilter.repository.ContactRepository;
import one.kastordriver.contactfilter.service.TrigramRegexContactFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/hello")
public class ContactFilterController {

    @Autowired
    TrigramRegexContactFinder trigramRegexContactFinder;

    @RequestMapping("/filter")
    public ResponseEntity<Contacts> filter(@RequestParam(value = "filterName") String filterName) {
        return ResponseEntity.ok()
                .body(trigramRegexContactFinder.findNotCoincidingForRegexContacts(Pattern.compile(filterName)));
    }
}
