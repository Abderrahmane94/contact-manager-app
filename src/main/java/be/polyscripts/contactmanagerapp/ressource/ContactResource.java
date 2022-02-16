package be.polyscripts.contactmanagerapp.ressource;

import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.service.ContactService;
import be.polyscripts.contactmanagerapp.validator.ContactValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@Tag(name = "Contact", description = "Endpoints for managing contact")
public class ContactResource {

    private final ContactService contactService;
    private final ContactValidator contactValidator;

    private final Logger LOGGER = LoggerFactory.logger(ContactResource.class);

    @Autowired
    public ContactResource(ContactService contactService, ContactValidator contactValidator) {
        this.contactService = contactService;
        this.contactValidator = contactValidator;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all contacts", description = "Find all contacts in the application",
            tags="Contact",responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class))),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<Contact>> getAllContacts() {
        LOGGER.info(" Entering getAllContacts API");
        List<Contact> contacts = contactService.findAllContacts();
        LOGGER.info(" Leaving getAllContacts API");
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        LOGGER.info(" Entering addContact API");
        contactValidator.validate(contact);
        Contact newContact = contactService.addContact(contact);
        LOGGER.info(" Leaving addContact API");
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Operation(summary = "Update an existing contact")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        LOGGER.info(" Entering updateContact API");
        contactValidator.validate(contact);
        Contact updateContact = contactService.updateContact(contact);
        LOGGER.info(" Leaving updateContact API");
        return new ResponseEntity<>(updateContact, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a contact by ID")
    public ResponseEntity<?> deleteContact(@PathVariable("id") Long id) {
        LOGGER.info(" Entering deleteContact API");
        contactService.deleteContact(id);
        LOGGER.info(" Leaving deleteContact API");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
