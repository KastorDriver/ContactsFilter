package one.kastordriver.contactfilter.repository;

import one.kastordriver.contactfilter.domain.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer> {

    public List<Contact> findByIdIn(Set<Integer> ids);
}
