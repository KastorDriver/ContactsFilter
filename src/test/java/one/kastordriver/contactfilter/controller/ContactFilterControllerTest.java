package one.kastordriver.contactfilter.controller;

import one.kastordriver.contactfilter.domain.Contact;
import one.kastordriver.contactfilter.domain.Contacts;
import one.kastordriver.contactfilter.service.TrigramRegexContactFinder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.regex.Pattern;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ContactFilterControllerTest {

    private MockMvc mockMvc;

    @Mock
    TrigramRegexContactFinder trigramRegexContactFinder;

    @InjectMocks
    ContactFilterController contactFilterController;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(contactFilterController).build();
    }

    @Test
    public void whenGetCorrectRegexThenReturnJsonAnsver() throws Exception {
        when(trigramRegexContactFinder.findNotCoincidingForRegexContacts(any(Pattern.class)))
                .thenReturn(new Contacts(Arrays.asList(
                        Contact.builder().id(1).name("Kent Beck").build(),
                        Contact.builder().id(1).name("Robert Martin").build()
                    )));

        MockHttpServletRequestBuilder request = get("/hello/filter")
                .param("filterName", "^Ke.*$");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"contacts\":[{\"id\":1,\"name\":\"Kent Beck\"},{\"id\":1,\"name\":\"Robert Martin\"}]}"));
    }
}
