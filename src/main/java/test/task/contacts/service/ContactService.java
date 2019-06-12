package test.task.contacts.service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.task.contacts.domain.Application;
import test.task.contacts.domain.Contact;
import test.task.contacts.dto.ApplicationDto;
import test.task.contacts.repository.ContactRepository;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Flux<Contact> getAll() {
        final Iterable<Contact> all = this.contactRepository.findAll();
        return Flux.fromIterable(all);
    }

    public Mono<Contact> getById(Long id) {
        return Mono.fromCallable(() -> this.contactRepository.findContactByContactId(id));
    }

    public Mono<ApplicationDto> getLastAppForContact(Long id) {
        return Mono.create(s -> {
            final Contact contact = this.contactRepository.findContactByContactId(id);
            if (contact == null) {
                s.success();
            } else {
                final List<Application> applications = contact.getApplications();
                applications.sort(Comparator.comparing(Application::getDtCreated,
                        Comparator.nullsFirst(Date::compareTo)).reversed());
                if (applications.size() == 0) {
                    s.success();
                } else {
                    System.out.println(applications.get(0).getDtCreated());
                    s.success(convertApp(applications.get(0)));
                }
            }
        });
    }

    private ApplicationDto convertApp(Application app) {
        return new ApplicationDto(app.getContact().getContactId(), app.getApplicationId(), app.getDtCreated(), app.getProductName());
    }
}
