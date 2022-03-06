package be.polyscripts.contactmanagerapp.repository;

import be.polyscripts.contactmanagerapp.model.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepositoryUnderTest;

    @Test
    void itShouldFindCompanyById() {

        //given
        Company company = new Company().builder().address("Alger").tva("TVA BE 159 789 963").build();
        companyRepositoryUnderTest.save(company);

        //when
        Company companyExpected = companyRepositoryUnderTest.findCompanyById(company.getId());

        //then
        assertNotNull(companyExpected);
        assertEquals(company, companyExpected);
    }
}