package one.kastordriver.contactfilter.service;

import one.kastordriver.contactfilter.domain.Contact;

import java.util.*;

/**
 * Created by Kastor on 4/6/2017.
 */
public class TrigramIndexCreator {

    private static final int SHIFT = 3;

    Set<String> prepareTrigrams(String contactName) {
        Set<String> trigrams = new HashSet<>();

        for (int pos = 0; pos + SHIFT <= contactName.length(); pos++) {
            trigrams.add(contactName.substring(pos, pos + SHIFT));
        }

        return trigrams;
    }

    public Map<String, Set<Integer>> createTrigramIndex(List<Contact> contacts) {
        Map<String, Set<Integer>> trigramIndex = new HashMap<>();

        for (Contact contact: contacts) {
            Set<String> trigrams = prepareTrigrams(contact.getName());

            for (String trigram: trigrams) {
                Set<Integer> occurrences = trigramIndex.getOrDefault(trigram, new HashSet<>());
                occurrences.add(contact.getId());
                trigramIndex.put(trigram, occurrences);
            }

        }

        return trigramIndex;
    }
}
