package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.repo.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

class CompanyServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    CompanyService companyService;

    @BeforeEach
    void init() {
        initMocks(this);
    }

    @Test
    void shouldReturnTheCompanyList() {
        Company company1 = new Company().builder().address("Alger").tva("TVA BE 951 753 852").build();
        Company company2 = new Company().builder().address("Bouzareha").tva("TVA BE 761 497 523").build();
        List<Company> companyList = Arrays.asList(company1, company2);

        when(companyRepository.findAll()).thenReturn(companyList);

        List<Company> companyListReturn = companyService.findAllCompanies();

        assertNotNull(companyListReturn);
        assertEquals(2, companyListReturn.size());
        assertEquals(company1, companyListReturn.get(0));
        assertEquals(company2, companyListReturn.get(1));
    }

    @Test
    void shouldAddCompany() {
        Company company = new Company().builder().address("Alger").tva("TVA BE 951 753 852").build();

        when(companyRepository.save(company)).thenReturn(company);

        Company company1 = companyService.addCompany(company);

        assertNotNull(company1);
        assertEquals(company, company1);
    }

    @Test
    void shouldUpdateCompany() {

        Company company = new Company().builder().address("Alger").tva("TVA BE 951 753 852").build();

        when(companyRepository.save(company)).thenReturn(company);

        Company company1 = company;
        company1.setAddress("Bouzareha");

        Company company2 = companyService.updateCompany(company1);

        assertNotNull(company2);
        assertEquals(company, company2);
        assertEquals("Bouzareha", company2.getAddress());
    }

}