package be.polyscripts.contactmanagerapp.repo;

import be.polyscripts.contactmanagerapp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyById(Long aLong);
}