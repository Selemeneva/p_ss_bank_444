package com.bank.publicinfo.controllers;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.mappers.LicenseMapper;
import com.bank.publicinfo.model.Certificate;
import com.bank.publicinfo.model.License;
import com.bank.publicinfo.service.BankDetailsServiceImpl;
import com.bank.publicinfo.service.LicenseServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import liquibase.pro.packaged.L;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class LicenseControllerTest {
    @Mock
    LicenseServiceImpl licenseService;

    @InjectMocks
    LicenseController controller;

    @Test
    void getAllLicenseList() {
        List<LicenseDto> expectedList = new ArrayList<>();
        LicenseDto  license1 = new LicenseDto();
        license1.setId(1L);
        LicenseDto  license2 = new LicenseDto();
        license2.setId(2L);

        expectedList.add(license1);
        expectedList.add(license2);
        when(licenseService.findAll()).thenReturn(expectedList);

        ResponseEntity<List<LicenseDto>> responseEntity = controller.getAllLicenseList();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedList, responseEntity.getBody());
    }

    @Test
    void getLicenseById() {
        LicenseDto licenseDto = new LicenseDto();
        when(licenseService.findById(1L)).thenReturn(licenseDto);
        LicenseDto foundLicense = controller.getLicenseById(1L).getBody();
        assertThat(foundLicense).isEqualTo(licenseDto);
        verify(licenseService,times(1)).findById(1L);
    }

    @Test
    void createLicense() {
        LicenseDto licenseDto = new LicenseDto();
        controller.createLicense(licenseDto);
       verify(licenseService, times(1)).save(licenseDto);
    }

    @Test
    void updateLicense() throws JsonProcessingException {
        LicenseDto licenseDto = new LicenseDto();
        controller.updateLicense(licenseDto,1L);
        verify(licenseService, times(1)).update(licenseDto, 1L);
    }

    @Test
    void deleteLicenseById() {
        ResponseEntity<HttpStatus> answer = controller.deleteLicenseById(1L);
        assertEquals(answer.getStatusCode(), HttpStatus.OK);
    }
}