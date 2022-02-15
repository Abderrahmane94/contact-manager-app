package be.polyscripts.contactmanagerapp.ressource;

import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.service.CompanyService;
import be.polyscripts.contactmanagerapp.service.ContactService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
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
    public ResponseEntity<List<Company>> getAllCompanies() {
        LOGGER.info(" Entering getAllCompanies API");
        List<Company> companies = companyService.findAllCompanies();
        LOGGER.info(" Leaving getAllCompanies API");
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        LOGGER.info(" Entering addCompany API");
        Company newCompany = companyService.addCompany(company);
        LOGGER.info(" Leaving addCompany API");
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        LOGGER.info(" Entering updateCompany API");
        Company updateCompany = companyService.updateCompany(company);
        LOGGER.info(" Leaving updateCompany API");
        return new ResponseEntity<>(updateCompany, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") Long id) {
        LOGGER.info(" Entering deleteCompany API");
        companyService.deleteCompany(id);
        LOGGER.info(" Leaving deleteCompany API");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{companyId}/contact")
    public ResponseEntity<Contact> addContactToCompany(@PathVariable("companyId") Long companyId, @RequestBody Contact contactRequest) {
        LOGGER.info(" Entering addContactToCompany API");
        Company company = companyService.findCompany(companyId);
        contactRequest.getCompanies().add(company);
        company.getContacts().add(contactRequest);
        Contact updatedContact = contactService.updateContact(contactRequest);
        LOGGER.info(" Leaving addContactToCompany API");
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

}
