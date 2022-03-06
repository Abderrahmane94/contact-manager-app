package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    ContactServiceImpl contactService;

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
}