package one.kastordriver.contactfilter.domain;

import lombok.Data;

@Data
public class Contacts {

    private Iterable<Contact> contacts;

    public Contacts(Iterable<Contact> contacts) {
        this.contacts = contacts;
    }
}
