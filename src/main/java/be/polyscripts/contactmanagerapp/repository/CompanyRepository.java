package be.polyscripts.contactmanagerapp.repository;

import be.polyscripts.contactmanagerapp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyById(Long companyID);

    Company findCompanyByUuid(UUID uuid);

    Boolean existsCompanyByUuid(UUID uuid);

    void deleteCompanyByUuid(UUID uuid);
}