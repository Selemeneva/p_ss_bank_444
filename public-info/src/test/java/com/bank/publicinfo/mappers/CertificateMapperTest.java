package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.model.BankDetails;
import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.model.Certificate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class CertificateMapperTest {

    @Mock
    private CertificateMapper certificateMapper;

    @Test
    public void toDtoTest() {
        certificateMapper = Mappers.getMapper(CertificateMapper.class);
        // Create a sample Certificate object with known data
        BankDetails bankDetails = new BankDetails();
        bankDetails.setId(1L);

        String stringToByte = "Hello, World!";
        byte[] byteArray = stringToByte.getBytes();

        Certificate certificate = new Certificate();
        certificate.setId(10L);
        certificate.setPhoto(byteArray);
        certificate.setBankDetails(bankDetails);

        // Call the mapper to convert the Certificate object to a Dto
        CertificateDto certificateDto = certificateMapper.toDto(certificate);

        // Verify that the conversion was correct
        assertNotNull(certificateDto);
        assertEquals(certificateDto.getId(), certificateDto.getId());
        assertEquals(certificateDto.getPhoto(), certificateDto.getPhoto());
        assertEquals(certificateDto.getBankDetailsId(), certificate.getBankDetails().getId());
    }

    @Test
    public void toEntityTest() {
        certificateMapper = Mappers.getMapper(CertificateMapper.class);
        String stringToByte = "Hello, World!";
        byte[] byteArray = stringToByte.getBytes();
        // Create a sample CertificateDto object with known data
        CertificateDto certificateDto = new CertificateDto();
        certificateDto.setId(10L);
        certificateDto.setPhoto(byteArray);
        certificateDto.setBankDetailsId(1L);

        // Call the mapper to convert the CertificateDto object to an entity
        Certificate certificate = certificateMapper.toEntity(certificateDto);

        // Verify that the conversion was correct
        assertNotNull(certificate);
        assertEquals(certificateDto.getId(), certificate.getId());
        assertEquals(certificateDto.getBankDetailsId(), certificate.getBankDetails().getId());
        assertEquals(certificateDto.getBankDetailsId(), certificate.getBankDetails().getId());
    }

    @Test
    public void toDtoListTest() {
        certificateMapper = Mappers.getMapper(CertificateMapper.class);
        // Create a list of sample Atm objects with known data
        BankDetails bankDetails = new BankDetails();
        bankDetails.setId(1L);

        String stringToByte = "Hello, World!";
        byte[] byteArray = stringToByte.getBytes();

        Certificate certificate = new Certificate();
        certificate.setId(10L);
        certificate.setPhoto(byteArray);
        certificate.setBankDetails(bankDetails);

        BankDetails bankDetails1 = new BankDetails();
        bankDetails1.setId(2L);

        Certificate certificate1 = new Certificate();
        certificate1.setId(20L);
        certificate1.setPhoto(byteArray);
        certificate1.setBankDetails(bankDetails1);

        List<Certificate> certificateList = new ArrayList<>();
        certificateList.add(certificate);
        certificateList.add(certificate1);

        // Call the mapper to convert the list of Certificate objects to a list of Dtos
        List<CertificateDto> certificateDtoList = certificateMapper.toDto(certificateList);

        // Verify that the conversion was correct
        assertNotNull(certificateDtoList);
        assertEquals(certificateList.size(), certificateDtoList.size());
        assertEquals(certificate.getId(), certificateDtoList.get(0).getId());
        assertArrayEquals(certificate.getPhoto(), certificateDtoList.get(0).getPhoto());
        assertEquals(certificate.getBankDetails().getId(), certificateDtoList.get(0).getBankDetailsId());
        assertEquals(certificate1.getId(), certificateDtoList.get(1).getId());
        assertArrayEquals(certificate1.getPhoto(), certificateDtoList.get(1).getPhoto());
        assertEquals(certificate1.getBankDetails().getId(), certificateDtoList.get(1).getBankDetailsId());
    }

    @Test
    public void toEntityListTest() {
        certificateMapper = Mappers.getMapper(CertificateMapper.class);
        // Create a list of sample CertificateDto objects with known data
        CertificateDto certificateDto = new CertificateDto();
        String stringToByte = "Hello, World!";
        String stringToByte1 = "Hello, Java!";
        byte[] byteArray = stringToByte.getBytes();
        byte[] byteArray1 = stringToByte1.getBytes();

        certificateDto.setId(10L);
        certificateDto.setPhoto(byteArray);
        certificateDto.setBankDetailsId(1L);

        CertificateDto certificateDto1 = new CertificateDto();
        certificateDto1.setId(20L);
        certificateDto1.setPhoto(byteArray1);
        certificateDto1.setBankDetailsId(2L);

        List<CertificateDto> certificateDtoList = new ArrayList<>();
        certificateDtoList.add(certificateDto);
        certificateDtoList.add(certificateDto1);

        // Call the mapper to convert the list of AtmDto objects to a list of entities
        List<Certificate> certificateList = certificateMapper.toEntity(certificateDtoList);

        // Verify that the conversion was correct
        assertNotNull(certificateList);
        assertEquals(certificateDtoList.size(), certificateList.size());
        assertEquals(certificateDto.getId(), certificateList.get(0).getId());
        assertArrayEquals(certificateDto.getPhoto(), certificateList.get(0).getPhoto());
        assertEquals(certificateDto.getBankDetailsId(), certificateList.get(0).getBankDetails().getId());
        assertEquals(certificateDto1.getId(), certificateList.get(1).getId());
        assertArrayEquals(certificateDto1.getPhoto(), certificateList.get(1).getPhoto());
        assertEquals(certificateDto1.getBankDetailsId(), certificateList.get(1).getBankDetails().getId());

    }
}