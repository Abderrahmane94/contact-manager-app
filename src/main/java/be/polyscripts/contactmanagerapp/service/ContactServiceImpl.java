package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.exceptions.ContactNotFoundException;
import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.repository.ContactRepository;
import be.polyscripts.contactmanagerapp.validator.ContactValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactValidator contactValidator = new ContactValidator();

    @Override
    public Contact addContact(Contact contact) {
        contactValidator.validate(contact);
        contact.setUuid(UUID.randomUUID());
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact contactToUpdate = contactRepository.findContactByUuid(contact.getUuid());
        if (contactToUpdate == null) throw new ContactNotFoundException("Contact to be updated not found");
        contact.setId(contactToUpdate.getId());
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(UUID uuid) {
        if (!contactRepository.existsContactByUuid(uuid)) {
            throw new ContactNotFoundException(
                    "Contact with uuid " + uuid + " does not exists");
        }
        Contact contact = this.findContactByUuid(uuid);
        if (contact.getCompanies() != null && !contact.getCompanies().isEmpty()) {
            for (Company company : contact.getCompanies()) {
                contact.removeCompany(company);
            }
        }
        contactRepository.deleteContactByUuid(uuid);
    }

    @Override
    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findContactByID(Long id) {
        return contactRepository.findContactById(id);
    }

    @Override
    public Contact findContactByUuid(UUID uuid) {
        return contactRepository.findContactByUuid(uuid);
    }


}
