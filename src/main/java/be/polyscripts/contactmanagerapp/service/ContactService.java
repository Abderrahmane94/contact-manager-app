package be.polyscripts.contactmanagerapp.service;


import be.polyscripts.contactmanagerapp.exceptions.ContactNotFoundException;
import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.repo.ContactRepository;
import be.polyscripts.contactmanagerapp.validator.ContactValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactValidator contactValidator = new ContactValidator();

    public Contact addContact(Contact contact) {
        contactValidator.validate(contact);
        return contactRepository.save(contact);
    }

    public Contact updateContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(
                    "Contact with id " + id + " does not exists");
        }
        Contact contact = this.findContactByID(id);
        if (contact.getCompanies() != null && !contact.getCompanies().isEmpty()) {
            for (Company company : contact.getCompanies()) {
                contact.removeCompany(company);
            }
        }
        contactRepository.deleteById(id);
    }

    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    public Contact findContactByID(Long id) {
        return contactRepository.findContactById(id);
    }
}
