package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.mappers.LicenseMapper;
import com.bank.publicinfo.model.License;
import com.bank.publicinfo.repositories.LicenseRepository;
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
class LicenseServiceImplTest {

    @Mock
    LicenseRepository licenseRepository;

    @InjectMocks
    LicenseServiceImpl licenseService;

    License license;
    LicenseDto licenseDto;

    @BeforeEach
    void setUp() throws IOException {
        String stringToByte = "Hello, Life!";
        byte[] byteArray = stringToByte.getBytes();
        licenseDto = LicenseDto.builder()
                .id(1L)
                .photo(byteArray)
                .build();
        license = License.builder()
                .id(1L)
                .photo(byteArray)
                .build();
        license = LicenseMapper.INSTANCE.toEntity(licenseDto);
    }

    @Test
    void save() {
        licenseService.save(LicenseMapper.INSTANCE.toDto(license));
        /**
         *ArgumentCaptor - утилитный класс, для "захвата" полей класса
         **/
        ArgumentCaptor<License> licenseCaptor = ArgumentCaptor.forClass(License.class);
        Mockito.verify(licenseRepository).save(licenseCaptor.capture());
        License savedLicense = licenseCaptor.getValue();
        assertNull(savedLicense.getId());
        assertArrayEquals(savedLicense.getPhoto(), licenseDto.getPhoto());
    }

    @Test
    void findAll() {
        // Given
        List<License> licenseList = new ArrayList<>();
        licenseList.add(license);
        Mockito.when(licenseRepository.findAll()).thenReturn(licenseList);
        // When
        List<LicenseDto> foundDtoList = licenseService.findAll();
        // Then
        Mockito.verify(licenseRepository, times(1)).findAll();
        assertEquals(foundDtoList.size(), licenseList.size());
        for (int i = 0; i < foundDtoList.size(); i++) {
            License foundLicense = LicenseMapper.INSTANCE.toEntity(foundDtoList.get(i));
            assertEquals(foundLicense.getId(), licenseList.get(i).getId());
            assertArrayEquals(foundLicense.getPhoto(), licenseList.get(i).getPhoto());
        }
    }

    @Test
    void existById() {
        when(licenseRepository.existsById(1L)).thenReturn(true);
        Boolean actual = licenseService.existById(1L);
        assertEquals(true, actual);
    }

    @Test
    void findById() {
        Mockito.when(licenseRepository.getReferenceById(1L)).thenReturn(license);
        // When
        LicenseDto foundDto = licenseService.findById(1L);
        // Then
        Mockito.verify(licenseRepository, times(1)).getReferenceById(1L);
        assertEquals(foundDto.getId(), license.getId());
    }

    @Test
    void update() throws JsonProcessingException {
        Long id = licenseDto.getId();
        licenseDto.setPhoto(ByteBuffer.allocate(5).putInt(8).array());
        licenseService.update(licenseDto, id);
        assertArrayEquals(licenseDto.getPhoto(), ByteBuffer.allocate(5).putInt(8).array());
    }

    @Test
    void deleteById() {
        long id = 1L;
        licenseService.deleteById(id);
        verify(licenseRepository).deleteById(id);
    }
}