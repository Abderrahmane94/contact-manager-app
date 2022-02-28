package be.polyscripts.contactmanagerapp.controller;

import be.polyscripts.contactmanagerapp.exceptions.CompanyNotFoundException;
import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.service.CompanyService;
import be.polyscripts.contactmanagerapp.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/api/v1/company")
@SecurityRequirement(name = "bearerAuth")
@CommonsLog
@Tag(name = "Company", description = "Endpoints for managing company")
public class CompanyController {

    private final CompanyService companyService;
    private final ContactService contactService;

    @Autowired
    public CompanyController(CompanyService companyService, ContactService contactService) {
        this.companyService = companyService;
        this.contactService = contactService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/all")
    @Operation(summary = "Get all companies", description = "Find all companies in the application",
            tags = "Company", responses = {
            @ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Company.class))),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<Company>> getAllCompanies() {
        log.info(" Entering getAllCompanies API");
        List<Company> companies = companyService.findAllCompanies();
        log.info(" Leaving getAllCompanies API");
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @Operation(summary = "Add a new company")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        log.info(" Entering addCompany API");
        Company newCompany = companyService.addCompany(company);
        log.info(" Leaving addCompany API");
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    @Operation(summary = "Update an existing company")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        log.info(" Entering updateCompany API");
        Company updateCompany = companyService.updateCompany(company);
        log.info(" Leaving updateCompany API");
        return new ResponseEntity<>(updateCompany, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{uuid}")
    @Operation(summary = "Delete a company by UUID")
    public ResponseEntity<?> deleteCompany(@PathVariable("uuid") UUID uuid) {
        log.info(" Entering deleteCompany API");
        companyService.deleteCompany(uuid);
        log.info(" Leaving deleteCompany API");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{companyUuid}/contact")
    @Operation(summary = "Add a contact to a company")
    public ResponseEntity<Contact> addContactToCompany(@PathVariable("companyUuid") UUID companyUuid, @RequestBody Contact contactRequest) {
        log.info(" Entering addContactToCompany API");
        Company company = companyService.findCompanyByUuid(companyUuid);
        if (company == null) throw new CompanyNotFoundException("Company to be added to the contact not found");
        contactRequest.addCompany(company);
        Contact updatedContact = contactService.updateContact(contactRequest);
        log.info(" Leaving addContactToCompany API");
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }
}