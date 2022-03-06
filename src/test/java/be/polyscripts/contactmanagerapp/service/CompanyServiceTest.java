package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.exceptions.CompanyNotFoundException;
import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    CompanyServiceImpl companyService;

    @BeforeEach
    void init() {
        initMocks(this);
    }

    @Test
    void itShouldReturnTheCompanyList() {
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
    void canAddCompany() {

        Company company = new Company().builder().address("Alger").tva("TVA BE 951 753 852").build();

        // when
        companyService.addCompany(company);

        // then
        ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);

        verify(companyRepository).save(companyArgumentCaptor.capture());

        Company capturedCompany = companyArgumentCaptor.getValue();

        assertEquals(capturedCompany, company);
    }

    @Test
    void canDeleteCompany() {
        // given
        UUID uuid = UUID.fromString("ba3d99c9-8f1a-4d4f-bdd9-600020f37401");
        given(companyRepository.existsCompanyByUuid(uuid))
                .willReturn(true);
        // when
        companyService.deleteCompany(uuid);

        // then
        verify(companyRepository).deleteCompanyByUuid(uuid);
    }

    @Test
    void willThrowWhenDeleteCompanyNotFound() {
        // given
        UUID uuid = UUID.fromString("ba3d99c9-8f1a-4d4f-bdd9-600020f37401");
        given(companyRepository.existsCompanyByUuid(uuid))
                .willReturn(false);
        // when
        // then
        assertThrows(CompanyNotFoundException.class, () -> companyService.deleteCompany(uuid), "Company with uuid " + uuid + " does not exists");

        verify(companyRepository, never()).deleteById(any());
    }
}