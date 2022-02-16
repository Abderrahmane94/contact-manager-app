package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.repo.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ContactServiceTest {

    @Mock private ContactRepository contactRepository;

    @InjectMocks
    ContactService contactService;

    @BeforeEach
    void init() {
        initMocks(this);
    }

    @Test
    void shouldReturnTheExpectedList() {

        Contact contact1 = new Contact().builder().firstName("Amin").lastName("belkadi").address("Alger").type("freelance").tva("TVA BE 951 753 852").build();
        Contact contact2 = new Contact().builder().firstName("Nadir").lastName("Benhocine").address("Alger").type("employe").build();
        List<Contact> contactList = Arrays.asList(contact1, contact2);

        when(contactRepository.findAll()).thenReturn(contactList);

        List<Contact> contactListReturn = contactService.findAllContacts();

        assertNotNull(contactListReturn);
        assertEquals(2, contactListReturn.size());
        assertEquals(contact1, contactListReturn.get(0));
        assertEquals(contact2, contactListReturn.get(1));

    }

    @Test
    void canAddContact() {
        Contact contact = new Contact().builder().firstName("Amin").lastName("belkadi").address("Alger").type("employe").build();

        when(contactRepository.save(contact)).thenReturn(contact);

        Contact contact1 = contactService.addContact(contact);

        assertNotNull(contact1);
        assertEquals(contact, contact1);
    }

    @Test
    void canUpdateContact() {

        Contact contact = new Contact().builder().address("Alger").tva("TVA BE 951 753 852").build();

        when(contactRepository.save(contact)).thenReturn(contact);

        Contact contact1 = contact;
        contact1.setAddress("Bouzareha");

        Contact contact2 = contactService.updateContact(contact1);

        assertNotNull(contact2);
        assertEquals(contact, contact2);
        assertEquals("Bouzareha", contact2.getAddress());
    }

}