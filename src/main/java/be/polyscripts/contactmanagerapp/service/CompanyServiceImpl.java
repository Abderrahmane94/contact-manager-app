package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.exceptions.CompanyNotFoundException;
import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@CommonsLog
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company addCompany(Company company) {
        company.setUuid(UUID.randomUUID());
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Company company) {
        Company companyToUpdate = companyRepository.findCompanyByUuid(company.getUuid());
        if (companyToUpdate == null) throw new CompanyNotFoundException("Company to be updated not found");
        company.setId(companyToUpdate.getId());
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(UUID uuid) {
        if (!companyRepository.existsCompanyByUuid(uuid)) {
            throw new CompanyNotFoundException(
                    "Company with uuid " + uuid + " does not exists");
        }
        companyRepository.deleteCompanyByUuid(uuid);
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompany(Long companyId) {
        return companyRepository.findCompanyById(companyId);
    }

    @Override
    public Company findCompanyByUuid(UUID uuid) {
        return companyRepository.findCompanyByUuid(uuid);
    }
}
