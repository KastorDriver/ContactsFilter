package one.kastordriver.contactfilter.service;

import one.kastordriver.contactfilter.domain.Contact;
import one.kastordriver.contactfilter.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class TrigramIndex {

    private static final int SHIFT = 3;

    private Map<String, Set<Integer>> trigramIndex;
    private Set<Integer> allContactId = new HashSet<>();

    @Autowired
    ContactRepository contactRepository;

    @PostConstruct
    public void init() {
        trigramIndex = createTrigramIndex(contactRepository.findAll());
    }

    Map<String, Set<Integer>> createTrigramIndex(Iterable<Contact> contacts) {
        Map<String, Set<Integer>> trigramIndex = new HashMap<>();

        for (Contact contact: contacts) {
            allContactId.add(contact.getId());

            Set<String> trigrams = prepareTrigrams(contact.getName());

            for (String trigram: trigrams) {
                Set<Integer> occurrences = trigramIndex.getOrDefault(trigram, new HashSet<>());
                occurrences.add(contact.getId());
                trigramIndex.put(trigram, occurrences);
            }

        }

        return trigramIndex;
    }

    Set<String> prepareTrigrams(String contactName) {
        Set<String> trigrams = new HashSet<>();

        for (int pos = 0; pos + SHIFT <= contactName.length(); pos++) {
            trigrams.add(contactName.substring(pos, pos + SHIFT));
        }

        return trigrams;
    }

    public Set<Integer> computeRegexNotCoincidingOccurrences(Pattern pattern) {
        Set<Integer> occurrences = new HashSet<>();

        for (String indexKey: trigramIndex.keySet()) {
            if (pattern.matcher(indexKey).matches()) {
                occurrences.addAll(trigramIndex.get(indexKey));
            }
        }

        return allContactId.stream()
                .filter(id -> !occurrences.contains(id))
                .collect(Collectors.toSet());
    }
}
