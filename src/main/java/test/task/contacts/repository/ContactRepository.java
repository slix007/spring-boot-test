package test.task.contacts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.task.contacts.domain.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    Contact findContactByContactId(Long contactId);

    Contact findContactByContactName(String contactName);

}
