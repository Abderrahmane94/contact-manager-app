package be.polyscripts.contactmanagerapp.ressource;

import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;


class ContactResourceTest {

    @Mock
    ContactService contactService;

    @InjectMocks
    ContactResource contactResource;

    @BeforeEach
    void init() {
        initMocks(this);
    }

    @Test
    void shouldReturnTheExpectedList() {

        Contact contact1 = new Contact().builder().firstName("Amin").lastName("belkadi").address("Alger").type("freelance").tva("TVA BE 951 753 852").build();
        Contact contact2 = new Contact().builder().firstName("Nadir").lastName("Benhocine").address("Alger").type("employe").build();
        List<Contact> contactList = Arrays.asList(contact1, contact2);

        when(contactService.findAllContacts()).thenReturn(contactList);

        List<Contact> contactListReturn = contactResource.getAllContacts().getBody();

        assertNotNull(contactListReturn);
        assertEquals(2, contactListReturn.size());
        assertEquals(contact1, contactListReturn.get(0));
        assertEquals(contact2, contactListReturn.get(1));

    }

    @Test @Disabled
    void shouldAddContact() {
        Contact contact = new Contact().builder().firstName("Amin").lastName("belkadi").address("Alger").type("employe").build();

        when(contactService.addContact(contact)).thenReturn(contact);

        Contact contact1 = contactResource.addContact(contact).getBody();

        assertNotNull(contact1);
        assertEquals(contact, contact1);
    }

    @Test @Disabled
    void shouldUpdateContact() {

        Contact contact = new Contact().builder().address("Alger").tva("TVA BE 951 753 852").build();

        when(contactService.updateContact(contact)).thenReturn(contact);

        Contact contact1 = contact;
        contact1.setAddress("Bouzareha");

        Contact contact2 = contactResource.updateContact(contact1).getBody();

        assertNotNull(contact2);
        assertEquals(contact, contact2);
        assertEquals("Bouzareha", contact2.getAddress());
    }

    @Test @Disabled
    void shouldDeleteContact() {

        Contact contact1 = new Contact().builder().address("Alger").tva("TVA BE 951 753 852").build();

        doNothing().when(contactService).deleteContact(contact1.getId());

        contactResource.deleteContact(contact1.getId());

        verify(contactService, times(1)).deleteContact(contact1.getId());
    }
}