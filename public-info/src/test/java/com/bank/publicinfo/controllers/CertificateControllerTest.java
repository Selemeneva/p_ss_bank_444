package com.bank.publicinfo.controllers;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.mappers.CertificateMapper;
import com.bank.publicinfo.model.Certificate;
import com.bank.publicinfo.service.BankDetailsServiceImpl;
import com.bank.publicinfo.service.CertificateServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
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
class CertificateControllerTest {
    @Mock
    CertificateServiceImpl certificateService;

    @Mock
    CertificateMapper certificateMapper;
    @InjectMocks
    CertificateController controller;

    @Test
    void getAllCertificateList() {
        List<CertificateDto> expectedList = new ArrayList<>();
        CertificateDto certificate1 = new CertificateDto();
        certificate1.setId(1L);
        CertificateDto  certificate2 = new CertificateDto();
        certificate1.setId(2L);

        expectedList.add(certificate1);
        expectedList.add(certificate2);
        when(certificateService.findAll()).thenReturn(expectedList);

        ResponseEntity<List<CertificateDto>> responseEntity = controller.getAllCertificateList();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedList, responseEntity.getBody());
    }

    @Test
    void getCertificateById() {
        CertificateDto certificateDto = new CertificateDto();
        when(certificateService.findById(1L)).thenReturn(certificateDto);
        CertificateDto foundCertificate = controller.getCertificateById(1L).getBody();
        assertThat(foundCertificate).isEqualTo(certificateDto);
        verify(certificateService,times(1)).findById(1L);
    }

    @Test
    void createCertificate() {
        CertificateDto certificateDto = new CertificateDto();
        controller.createCertificate(certificateDto);
        Certificate certificate = certificateMapper.toEntity(certificateDto);
        verify(certificateService, times(1)).save(certificateDto);
    }

    @Test
    void updateCertificate() throws JsonProcessingException {
        CertificateDto certificateDto = new CertificateDto();
        controller.updateCertificate(certificateDto,1L);
        verify(certificateService, times(1)).update(certificateDto, 1L);
    }

    @Test
    void deleteCertificateById() {
        ResponseEntity<HttpStatus> answer = controller.deleteCertificateById(1L);
        assertEquals(answer.getStatusCode(), HttpStatus.OK);

    }
}