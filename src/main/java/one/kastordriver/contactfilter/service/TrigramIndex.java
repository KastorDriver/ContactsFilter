package one.kastordriver.contactfilter.service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kastor on 4/6/2017.
 */
public class TrigramIndex {

    private static final int SHIFT = 3;

    public Set<String> prepareTrigrams(String contactName) {
        Set<String> trigrams = new HashSet<>();

        for (int pos = 0; pos + SHIFT <= contactName.length(); pos++) {
            trigrams.add(contactName.substring(pos, pos + SHIFT));
        }

        return trigrams;
    }
}
