package test.task.contacts;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import test.task.contacts.domain.Application;
import test.task.contacts.domain.Contact;
import test.task.contacts.repository.ContactRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContactsApplication.class)
@Transactional
public class ContactsApplicationTests {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void contextLoads() {
        final Contact testContact = new Contact();
        testContact.setContactName("testContact");
        final Application app = new Application();
        app.setContact(testContact);
        app.setDtCreated(new Date());
        app.setProductName("AwesomeProduct");
        testContact.addApplication(app);

        contactRepository.save(testContact);

        final Contact contact2 = new Contact();
        contact2.setContactName("newContName2");
        contactRepository.save(contact2);

        final Iterable<Contact> all = contactRepository.findAll();
        System.out.println("All Contacts: " + all);
        final Contact byId = contactRepository.findContactByContactName("testContact");
        Assert.assertEquals(testContact, byId);
        final Contact byIdNone = contactRepository.findContactByContactId(7000L);
        Assert.assertNull(byIdNone);
    }

}
