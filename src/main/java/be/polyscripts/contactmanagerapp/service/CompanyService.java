package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.model.Company;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    Company addCompany(Company company);

    Company updateCompany(Company company);

    void deleteCompany(UUID uuid);

    List<Company> findAllCompanies();

    Company findCompany(Long companyId);

    Company findCompanyByUuid(UUID uuid);
}
