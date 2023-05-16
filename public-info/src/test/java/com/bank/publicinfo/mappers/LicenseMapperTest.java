package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.model.BankDetails;
import com.bank.publicinfo.model.License;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class LicenseMapperTest {

    @Mock
    private LicenseMapper licenseMapper;

    @Test
    public void toDtoTest() {
        licenseMapper = Mappers.getMapper(LicenseMapper.class);
        // Create a sample License object with known data
        BankDetails bankDetails = new BankDetails();
        bankDetails.setId(1L);

        String stringToByte = "Hello, World!";
        byte[] byteArray = stringToByte.getBytes();

        License license = new License();
        license.setId(10L);
        license.setPhoto(byteArray);
        license.setBankDetails(bankDetails);

        // Call the mapper to convert the License object to a Dto
        LicenseDto licenseDto = licenseMapper.toDto(license);

        // Verify that the conversion was correct
        assertNotNull(licenseDto);
        assertEquals(licenseDto.getId(), licenseDto.getId());
        assertArrayEquals(licenseDto.getPhoto(), license.getPhoto());
        assertEquals(licenseDto.getBankDetailsId(), license.getBankDetails().getId());
    }

    @Test
    public void toEntityTest() {
        licenseMapper = Mappers.getMapper(LicenseMapper.class);
        String stringToByte = "Hello, World!";
        byte[] byteArray = stringToByte.getBytes();
        // Create a sample LicenseDto object with known data
        LicenseDto licenseDto = new LicenseDto();
        licenseDto.setId(10L);
        licenseDto.setPhoto(byteArray);
        licenseDto.setBankDetailsId(1L);

        // Call the mapper to convert the LicenseDto object to an entity
        License license = licenseMapper.toEntity(licenseDto);

        // Verify that the conversion was correct
        assertNotNull(license);
        assertEquals(licenseDto.getId(), license.getId());
        assertEquals(licenseDto.getBankDetailsId(), license.getBankDetails().getId());
        assertEquals(licenseDto.getBankDetailsId(), license.getBankDetails().getId());
    }

    @Test
    public void toDtoListTest() {
        licenseMapper = Mappers.getMapper(LicenseMapper.class);
        // Create a list of sample License objects with known data
        BankDetails bankDetails = new BankDetails();
        bankDetails.setId(1L);

        String stringToByte = "Hello, World!";
        byte[] byteArray = stringToByte.getBytes();

        License license = new License();
        license.setId(10L);
        license.setPhoto(byteArray);
        license.setBankDetails(bankDetails);

        BankDetails bankDetails1 = new BankDetails();
        bankDetails1.setId(2L);

        License license1 = new License();
        license1.setId(20L);
        license1.setPhoto(byteArray);
        license1.setBankDetails(bankDetails1);

        List<License> licenseList = new ArrayList<>();
        licenseList.add(license);
        licenseList.add(license1);

        // Call the mapper to convert the list of License objects to a list of Dtos
        List<LicenseDto> licenseDtoList = licenseMapper.toDto(licenseList);

        // Verify that the conversion was correct
        assertNotNull(licenseDtoList);
        assertEquals(licenseList.size(), licenseDtoList.size());
        assertEquals(license.getId(), licenseDtoList.get(0).getId());
        assertArrayEquals(license.getPhoto(), licenseDtoList.get(0).getPhoto());
        assertEquals(license.getBankDetails().getId(), licenseDtoList.get(0).getBankDetailsId());
        assertEquals(license1.getId(), licenseDtoList.get(1).getId());
        assertArrayEquals(license1.getPhoto(), licenseDtoList.get(1).getPhoto());
        assertEquals(license1.getBankDetails().getId(), licenseDtoList.get(1).getBankDetailsId());
    }

    @Test
    public void toEntityListTest() {
        licenseMapper = Mappers.getMapper(LicenseMapper.class);
        // Create a list of sample LicenseDto objects with known data

        String stringToByte = "Hello, World!";
        String stringToByte1 = "Hello, Java!";
        byte[] byteArray = stringToByte.getBytes();
        byte[] byteArray1 = stringToByte1.getBytes();

        LicenseDto licenseDto = new LicenseDto();
        licenseDto.setId(10L);
        licenseDto.setPhoto(byteArray);
        licenseDto.setBankDetailsId(1L);

        LicenseDto licenseDto1 = new LicenseDto();
        licenseDto1.setId(20L);
        licenseDto1.setPhoto(byteArray1);
        licenseDto1.setBankDetailsId(2L);

        List<LicenseDto> licenseDtoList = new ArrayList<>();
        licenseDtoList.add(licenseDto);
        licenseDtoList.add(licenseDto1);

        // Call the mapper to convert the list of LicenseDto objects to a list of entities
        List<License> licenseList = licenseMapper.toEntity(licenseDtoList);

        // Verify that the conversion was correct
        assertNotNull(licenseList);
        assertEquals(licenseDtoList.size(), licenseList.size());
        assertEquals(licenseDto.getId(), licenseList.get(0).getId());
        assertArrayEquals(licenseDto.getPhoto(), licenseList.get(0).getPhoto());
        assertEquals(licenseDto.getBankDetailsId(), licenseList.get(0).getBankDetails().getId());
        assertEquals(licenseDto1.getId(), licenseList.get(1).getId());
        assertArrayEquals(licenseDto1.getPhoto(), licenseList.get(1).getPhoto());
        assertEquals(licenseDto1.getBankDetailsId(), licenseList.get(1).getBankDetails().getId());

    }
}