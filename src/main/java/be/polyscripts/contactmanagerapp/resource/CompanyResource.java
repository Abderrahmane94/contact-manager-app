package be.polyscripts.contactmanagerapp.resource;

import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.model.Contact;
import be.polyscripts.contactmanagerapp.service.CompanyService;
import be.polyscripts.contactmanagerapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyResource {


    private CompanyService companyService;
    private ContactService contactService;


    @Autowired
    public CompanyResource(CompanyService companyService,ContactService contactService) {
        this.companyService = companyService;
        this.contactService = contactService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.findAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        Company newCompany = companyService.addCompany(company);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        Company updateCompany = companyService.updateCompany(company);
        return new ResponseEntity<>(updateCompany, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{companyId}/contact")
    public ResponseEntity<Contact> addContactToCompany(@PathVariable("companyId") Long companyId, @RequestBody Contact contactRequest) {
        Company company = companyService.findCompany(companyId);
        contactRequest.getCompanies().add(company);
        company.getContacts().add(contactRequest);
        Contact updatedContact = contactService.updateContact(contactRequest);
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }


}
