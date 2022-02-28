package be.polyscripts.contactmanagerapp.controller;

import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.service.ContactService;
import be.polyscripts.contactmanagerapp.validator.ContactValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/contact")
@Tag(name = "Contact", description = "Endpoints for managing contact")
@CommonsLog
public class ContactController {

    private final ContactService contactService;
    private final ContactValidator contactValidator;

    @Autowired
    public ContactController(ContactService contactService, ContactValidator contactValidator) {
        this.contactService = contactService;
        this.contactValidator = contactValidator;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/all")
    @Operation(summary = "Get all contacts", description = "Find all contacts in the application",
            tags = "Contact", responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class))),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<Contact>> getAllContacts() {
        log.info(" Entering getAllContacts API");
        List<Contact> contacts = contactService.findAllContacts();
        log.info(" Leaving getAllContacts API");
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @Operation(summary = "Add a new contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        log.info(" Entering addContact API");
        contactValidator.validate(contact);
        Contact newContact = contactService.addContact(contact);
        log.info(" Leaving addContact API");
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    @Operation(summary = "Update an existing contact")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        log.info(" Entering updateContact API");
        contactValidator.validate(contact);
        Contact updateContact = contactService.updateContact(contact);
        log.info(" Leaving updateContact API");
        return new ResponseEntity<>(updateContact, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{uuid}")
    @Operation(summary = "Delete a contact by ID")
    public ResponseEntity<?> deleteContact(@PathVariable("uuid") UUID uuid) {
        log.info(" Entering deleteContact API");
        contactService.deleteContact(uuid);
        log.info(" Leaving deleteContact API");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}