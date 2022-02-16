package be.polyscripts.contactmanagerapp.service;

import be.polyscripts.contactmanagerapp.exceptions.CompanyNotFoundException;
import be.polyscripts.contactmanagerapp.model.Company;
import be.polyscripts.contactmanagerapp.repo.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class CompanyServiceTest {

    @Mock private CompanyRepository companyRepository;

    @InjectMocks private CompanyService companyService;

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

        assertThat(capturedCompany).isEqualTo(company);
    }

    @Test
    void canDeleteCompany() {
        // given
        long id = 1;
        given(companyRepository.existsById(id))
                .willReturn(true);
        // when
        companyService.deleteCompany(id);

        // then
        verify(companyRepository).deleteById(id);
    }

    @Test
    void willThrowWhenDeleteCompanyNotFound() {
        // given
        long id = 1;
        given(companyRepository.existsById(id))
                .willReturn(false);
        // when
        // then
        assertThatThrownBy(() -> companyService.deleteCompany(id))
                .isInstanceOf(CompanyNotFoundException.class)
                .hasMessageContaining("Company with id " + id + " does not exists");

        verify(companyRepository, never()).deleteById(any());
    }


}