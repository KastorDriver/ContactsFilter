package one.kastordriver.contactfilter.service;

import one.kastordriver.contactfilter.domain.Contacts;

import java.util.regex.Pattern;

public interface RegexContactFinder {

    Contacts findNotCoincidingForRegexContacts(Pattern pattern);
}
