package be.polyscripts.contactmanagerapp.ressource;

import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.service.CompanyService;
import be.polyscripts.contactmanagerapp.service.ContactService;
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
@RequestMapping("/company")
@Tag(name = "Company", description = "Endpoints for managing company")
public class CompanyResource {

    private final CompanyService companyService;
    private final ContactService contactService;

    private final Logger LOGGER = LoggerFactory.logger(CompanyResource.class);

    @Autowired
    public CompanyResource(CompanyService companyService,ContactService contactService) {
        this.companyService = companyService;
        this.contactService = contactService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all companies", description = "Find all companies in the application",
               tags="Company",responses = {
                                @ApiResponse(
                                        description = "Success",
                                        responseCode = "200",
                                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Company.class))),
                                @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
                                })
    public ResponseEntity<List<Company>> getAllCompanies() {
        LOGGER.info(" Entering getAllCompanies API");
        List<Company> companies = companyService.findAllCompanies();
        LOGGER.info(" Leaving getAllCompanies API");
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new company")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        LOGGER.info(" Entering addCompany API");
        Company newCompany = companyService.addCompany(company);
        LOGGER.info(" Leaving addCompany API");
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Operation(summary = "Update an existing company")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        LOGGER.info(" Entering updateCompany API");
        Company updateCompany = companyService.updateCompany(company);
        LOGGER.info(" Leaving updateCompany API");
        return new ResponseEntity<>(updateCompany, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a company by ID")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") Long id) {
        LOGGER.info(" Entering deleteCompany API");
        companyService.deleteCompany(id);
        LOGGER.info(" Leaving deleteCompany API");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{companyId}/contact")
    @Operation(summary = "Add a contact to a company")
    public ResponseEntity<Contact> addContactToCompany(@PathVariable("companyId") Long companyId, @RequestBody Contact contactRequest) {
        LOGGER.info(" Entering addContactToCompany API");
        Company company = companyService.findCompany(companyId);
        contactRequest.addCompany(company);
        Contact updatedContact = contactService.updateContact(contactRequest);
        LOGGER.info(" Leaving addContactToCompany API");
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

}
