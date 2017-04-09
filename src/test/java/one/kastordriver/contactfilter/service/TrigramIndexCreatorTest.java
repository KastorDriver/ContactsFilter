package one.kastordriver.contactfilter.service;

import one.kastordriver.contactfilter.domain.Contact;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Kastor on 4/6/2017.
 */
public class TrigramIndexCreatorTest {

    private TrigramIndexCreator trigramIndexCreator;

    @Before
    public void before() {
        trigramIndexCreator = new TrigramIndexCreator();
    }

    @Test
    public void whenGetContactNameThenReturnSetOfTrigrams() {
        Set<String> trigrams = trigramIndexCreator.prepareTrigrams("Adrian Smit");
        assertThat(trigrams, containsInAnyOrder("Adr", "dri", "ria", "ian", "an ", "n S", " Sm", "Smi", "mit"));
    }

    @Test
    public void whenGetContactsThenCreateTrigramIndex() {
        List<Contact> contacts = Arrays.asList(
                Contact.builder().id(1).name("Kent Beck").build(),
                Contact.builder().id(2).name("Martin Fowler").build(),
                Contact.builder().id(3).name("Robert Martin").build()
        );

        Map<String, Set<Integer>> trigramIndex = trigramIndexCreator.createTrigramIndex(contacts);

        assertThat(trigramIndex.get("Ken"), containsInAnyOrder(1));
        assertThat(trigramIndex.get("ent"), containsInAnyOrder(1));
        assertThat(trigramIndex.get("nt "), containsInAnyOrder(1));
        assertThat(trigramIndex.get("t B"), containsInAnyOrder(1));
        assertThat(trigramIndex.get(" Be"), containsInAnyOrder(1));
        assertThat(trigramIndex.get("Bec"), containsInAnyOrder(1));
        assertThat(trigramIndex.get("eck"), containsInAnyOrder(1));
        assertThat(trigramIndex.get("Mar"), containsInAnyOrder(2, 3));
        assertThat(trigramIndex.get("art"), containsInAnyOrder(2, 3));
        assertThat(trigramIndex.get("rti"), containsInAnyOrder(2, 3));
        assertThat(trigramIndex.get("tin"), containsInAnyOrder(2, 3));
        assertThat(trigramIndex.get("in "), containsInAnyOrder(2));
        assertThat(trigramIndex.get("n F"), containsInAnyOrder(2));
        assertThat(trigramIndex.get(" Fo"), containsInAnyOrder(2));
        assertThat(trigramIndex.get("Fow"), containsInAnyOrder(2));
        assertThat(trigramIndex.get("owl"), containsInAnyOrder(2));
        assertThat(trigramIndex.get("wle"), containsInAnyOrder(2));
        assertThat(trigramIndex.get("ler"), containsInAnyOrder(2));
        assertThat(trigramIndex.get("Rob"), containsInAnyOrder(3));
        assertThat(trigramIndex.get("obe"), containsInAnyOrder(3));
        assertThat(trigramIndex.get("ber"), containsInAnyOrder(3));
        assertThat(trigramIndex.get("ert"), containsInAnyOrder(3));
        assertThat(trigramIndex.get("rt "), containsInAnyOrder(3));
        assertThat(trigramIndex.get("t M"), containsInAnyOrder(3));
        assertThat(trigramIndex.get(" Ma"), containsInAnyOrder(3));
    }


}
