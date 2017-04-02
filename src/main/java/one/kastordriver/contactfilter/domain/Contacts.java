package one.kastordriver.contactfilter.domain;

import lombok.Data;

import java.util.List;

@Data
public class Contacts {

    private List<Contact> contacts;

    public Contacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
