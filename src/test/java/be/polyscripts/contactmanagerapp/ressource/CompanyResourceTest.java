package be.polyscripts.contactmanagerapp.ressource;

import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


class CompanyResourceTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyResource companyResource;

    @BeforeEach
    void init() {
        initMocks(this);
    }

    @Test
    void shouldReturnTheExpectedList() {

        Company company1 = new Company().builder().address("Alger").tva("TVA BE 951 753 852").build();
        Company company2 = new Company().builder().address("Bouzareha").tva("TVA BE 761 497 523").build();
        List<Company> companyList = Arrays.asList(company1, company2);

        when(companyService.findAllCompanies()).thenReturn(companyList);

        List<Company> companyListReturn = companyResource.getAllCompanies().getBody();

        assertNotNull(companyListReturn);
        assertEquals(2, companyListReturn.size());
        assertEquals(company1, companyListReturn.get(0));
        assertEquals(company2, companyListReturn.get(1));

    }

    @Test
    void shouldAddCompany() {
        Company company = new Company().builder().address("Alger").tva("TVA BE 951 753 852").build();

        when(companyService.addCompany(company)).thenReturn(company);

        Company company1 = companyResource.addCompany(company).getBody();

        assertNotNull(company1);
        assertEquals(company, company1);
    }

    @Test
    void shouldUpdateCompany() {

        Company company = new Company().builder().address("Alger").tva("TVA BE 951 753 852").build();

        when(companyService.updateCompany(company)).thenReturn(company);

        Company company1 = company;
        company1.setAddress("Bouzareha");

        Company company2 = companyResource.updateCompany(company1).getBody();

        assertNotNull(company2);
        assertEquals(company, company2);
        assertEquals("Bouzareha", company2.getAddress());
    }

    @Test
    void shouldDeleteCompany() {

        Company company1 = new Company().builder().address("Alger").tva("TVA BE 951 753 852").build();

        doNothing().when(companyService).deleteCompany(company1.getId());

        companyResource.deleteCompany(company1.getId());

        verify(companyService, times(1)).deleteCompany(company1.getId());
    }


}