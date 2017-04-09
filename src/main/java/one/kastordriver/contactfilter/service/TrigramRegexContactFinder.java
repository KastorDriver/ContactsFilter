package one.kastordriver.contactfilter.service;

import one.kastordriver.contactfilter.domain.Contacts;
import one.kastordriver.contactfilter.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class TrigramRegexContactFinder implements RegexContactFinder {

    @Autowired
    TrigramIndex trigramIndex;

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contacts findNotCoincidingForRegexContacts(Pattern pattern) {
        Set<Integer> notCoincidingOccurrences = trigramIndex.computeRegexNotCoincidingOccurrences(pattern);

        if (notCoincidingOccurrences.isEmpty()) {
            return new Contacts(Collections.EMPTY_SET);
        } else {
            return new Contacts(contactRepository.findByIdIn(notCoincidingOccurrences));
        }
    }
}
