package one.kastordriver.contactfilter.service;

import one.kastordriver.contactfilter.domain.Contacts;
import one.kastordriver.contactfilter.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrigramRegexContactFinderTest {

    @Mock
    TrigramIndex trigramIndex;

    @Mock
    ContactRepository contactRepository;

    @InjectMocks
    TrigramRegexContactFinder trigramRegexContactFinder;

    @Test
    public void whenDoNotFoundFindNotCoincidingForRegexContactsThenReturnEmptySet() {
        when(trigramIndex.computeRegexNotCoincidingOccurrences(any(Pattern.class)))
                .thenReturn(new HashSet<Integer>());
        Contacts contacts = trigramRegexContactFinder.findNotCoincidingForRegexContacts(Pattern.compile("someRegex"));
        assertFalse(contacts.getContacts().iterator().hasNext());
    }

    @Test
    public void whenFoundNotCoincidingForRegexContactsThenSelectByFoundedIds() {
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));

        when(trigramIndex.computeRegexNotCoincidingOccurrences(any(Pattern.class))).thenReturn(set);
        trigramRegexContactFinder.findNotCoincidingForRegexContacts(Pattern.compile("someRegex"));
        verify(contactRepository, times(1)).findByIdIn(set);
    }
}
