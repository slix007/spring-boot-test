package test.task.contacts.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import test.task.contacts.domain.Application;
import test.task.contacts.domain.Contact;
import test.task.contacts.dto.ApplicationDto;
import test.task.contacts.service.ContactService;

@RestController
@Transactional
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public Flux<Contact> getAll() {
        return contactService.getAll();
    }

    @GetMapping("/contact/{id}")
    public Mono<Contact> getById(@PathVariable Long id) {
        return contactService.getById(id);
    }

    @GetMapping("/contact/{id}/lastapp")
    public Mono<ApplicationDto> getLastApp(@PathVariable Long id) {
        return contactService.getLastAppForContact(id);
    }

}
