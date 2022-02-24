package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.model.Contact;

import java.util.List;
import java.util.UUID;

public interface ContactService {

    Contact addContact(Contact contact);

    Contact updateContact(Contact contact);

    void deleteContact(UUID uuid);

    List<Contact> findAllContacts();

    Contact findContactByID(Long id);

    Contact findContactByUuid(UUID uuid);
}
