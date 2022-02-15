package be.polyscripts.contactmanagerapp.service;


import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.repo.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {this.companyRepository = companyRepository;}

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    public List<Company> findAllCompanies() { return companyRepository.findAll();}

    public Company findCompany(Long companyId) {
        return companyRepository.findCompanyById(companyId);
    }
}
