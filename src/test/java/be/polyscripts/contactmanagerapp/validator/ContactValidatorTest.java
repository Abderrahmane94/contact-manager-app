package be.polyscripts.contactmanagerapp.validator;

import be.polyscripts.contactmanagerapp.exceptions.FreelanceWithNoTVAException;
import be.polyscripts.contactmanagerapp.model.Contact;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.initMocks;

class ContactValidatorTest {

    void init() {
        initMocks(this);
    }

    @Test
    void shouldReturnFreelanceWithNoTVAException() {

        ContactValidator contactValidator = new ContactValidator();

        Contact contact = Contact.builder().firstName("Amin").lastName("Belkadi").address("Bouzareha").type("freelance").build();

        assertThrows(FreelanceWithNoTVAException.class,()->contactValidator.validate(contact));

    }

}