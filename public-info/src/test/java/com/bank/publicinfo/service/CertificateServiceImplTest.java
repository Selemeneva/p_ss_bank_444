package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.mappers.CertificateMapper;
import com.bank.publicinfo.model.Certificate;
import com.bank.publicinfo.repositories.CertificateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class CertificateServiceImplTest {

    @Mock
    CertificateRepository certificateRepository;

    @InjectMocks
    CertificateServiceImpl certificateService;

    Certificate certificate;
    CertificateDto certificateDto;

    @BeforeEach
    void setUp() throws IOException {
        String stringToByte = "Hello, World!";
        byte[] byteArray = stringToByte.getBytes();
      certificateDto = CertificateDto.builder()
              .id(1L)
              .photo(byteArray)
              .build();
        certificate = Certificate.builder()
                .id(1L)
                .photo(byteArray)
                .build();
        certificate = CertificateMapper.INSTANCE.toEntity(certificateDto);
    }

    @Test
    void save() {
        certificateService.save(CertificateMapper.INSTANCE.toDto(certificate));
        /**
         *ArgumentCaptor - утилитный класс, для "захвата" полей класса
         **/
        ArgumentCaptor<Certificate> certificateCaptor = ArgumentCaptor.forClass(Certificate.class);
        Mockito.verify(certificateRepository).save(certificateCaptor.capture());
        Certificate savedCertificate = certificateCaptor.getValue();
        assertNull(savedCertificate.getId());
        assertArrayEquals(savedCertificate.getPhoto(), certificateDto.getPhoto());
    }

    @Test
    void findAll() {
        // Given
        List<Certificate> certificateList = new ArrayList<>();
        certificateList.add(certificate);
        Mockito.when(certificateRepository.findAll()).thenReturn(certificateList);
        // When
        List<CertificateDto> foundDtoList = certificateService.findAll();
        // Then
        Mockito.verify(certificateRepository, times(1)).findAll();
        assertEquals(foundDtoList.size(), certificateList.size());
        for (int i = 0; i < foundDtoList.size(); i++) {
            Certificate foundCertificate = CertificateMapper.INSTANCE.toEntity(foundDtoList.get(i));
            assertEquals(foundCertificate.getId(), certificateList.get(i).getId());
            assertArrayEquals(foundCertificate.getPhoto(), certificateList.get(i).getPhoto());
        }
    }

    @Test
    void existById() {
        when(certificateRepository.existsById(1L)).thenReturn(true);
        Boolean actual = certificateService.existById(1L);
        assertEquals(true, actual);
    }

    @Test
    void findById() {
        Mockito.when(certificateRepository.getReferenceById(1L)).thenReturn(certificate);
        // When
        CertificateDto foundDto = certificateService.findById(1L);
        // Then
        Mockito.verify(certificateRepository, times(1)).getReferenceById(1L);
        assertEquals(foundDto.getId(), certificate.getId());
    }

    @Test
    void update() throws JsonProcessingException {
        Long id = certificateDto.getId();
        certificateDto.setPhoto(ByteBuffer.allocate(5).putInt(8).array());
        certificateService.update(certificateDto, id);
        assertArrayEquals(certificateDto.getPhoto(), ByteBuffer.allocate(5).putInt(8).array());
    }

    @Test
    void deleteById() {
        long id = 1L;
        certificateService.deleteById(id);
        verify(certificateRepository).deleteById(id);
    }
}