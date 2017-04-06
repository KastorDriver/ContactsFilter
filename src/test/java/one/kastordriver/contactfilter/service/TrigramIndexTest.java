package one.kastordriver.contactfilter.service;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Kastor on 4/6/2017.
 */
public class TrigramIndexTest {

    @Test
    public void whenGetContactNameThenReturnTrigrams() {
        TrigramIndex trigramIndex = new TrigramIndex();
        Set<String> trigrams = trigramIndex.prepareTrigrams("Adrian Smit");

        assertThat(trigrams, hasItems("Adr", "dri", "ria", "ian", "an ", "n S", " Sm", "Smi", "mit"));
        assertEquals(9, trigrams.size());
    }


}
