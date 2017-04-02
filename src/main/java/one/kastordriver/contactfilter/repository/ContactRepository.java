package one.kastordriver.contactfilter.repository;

import one.kastordriver.contactfilter.domain.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer> {}
