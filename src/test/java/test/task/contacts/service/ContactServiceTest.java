package test.task.contacts.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import test.task.contacts.domain.Application;
import test.task.contacts.domain.Contact;
import test.task.contacts.dto.ApplicationDto;
import test.task.contacts.repository.ContactRepository;

public class ContactServiceTest {

    private ContactService contactService;

    private ContactRepository contactRepositoryMock;

    @Before
    public void setUp() {
        contactRepositoryMock = Mockito.mock(ContactRepository.class);
        contactService = new ContactService(contactRepositoryMock);
    }

    @Test
    public void getLastApp() {
        final Contact contact = new Contact();
        contact.setContactId(10L);
        contact.setContactName("testContact");
        final List<Application> apps = new ArrayList<>();
        contact.setApplications(apps);

        final Application a1 = new Application();
        a1.setContact(contact);
        a1.setDtCreated(Date.from(Instant.now().minus(10, ChronoUnit.MINUTES)));
        a1.setProductName("prod1");
        apps.add(a1);

        final Application a2 = new Application();
        a2.setContact(contact);
        a2.setDtCreated(Date.from(Instant.now().minus(3, ChronoUnit.MINUTES)));
        a2.setProductName("prod2-latest");
        apps.add(a2);

        final Application a3 = new Application();
        a3.setContact(contact);
        a3.setDtCreated(Date.from(Instant.now().minus(20, ChronoUnit.MINUTES)));
        a3.setProductName("prod3");
        apps.add(a3);

        Mockito.when(contactRepositoryMock.findContactByContactId(10L)).thenReturn(contact);

        final Mono<ApplicationDto> lastAppForContact = contactService.getLastAppForContact(10L);
        System.out.println(lastAppForContact.block());
        Assert.assertEquals(Objects.requireNonNull(lastAppForContact.block()).getProductName(), "prod2-latest");

    }

    @Test
    public void getLastAppNone() {
        final Contact contact = new Contact();
        contact.setContactId(10L);
        contact.setContactName("testNone");
        final List<Application> apps = new ArrayList<>();
        contact.setApplications(apps);

        Mockito.when(contactRepositoryMock.findContactByContactId(10L)).thenReturn(contact);

        final Mono<ApplicationDto> lastAppForContact = contactService.getLastAppForContact(10L);

        System.out.println(lastAppForContact.block());
        Assert.assertNull(lastAppForContact.block());

        System.out.println(lastAppForContact.blockOptional());
        Assert.assertEquals(Optional.empty(), lastAppForContact.blockOptional());
    }

    @Test
    public void getLastAppNull() {
        Mockito.when(contactRepositoryMock.findContactByContactId(10L)).thenReturn(null);
        final Mono<ApplicationDto> lastAppForContact = contactService.getLastAppForContact(10L);

        System.out.println(lastAppForContact.block());
        Assert.assertNull(lastAppForContact.block());

        System.out.println(lastAppForContact.blockOptional());
        Assert.assertEquals(Optional.empty(), lastAppForContact.blockOptional());
    }
}
