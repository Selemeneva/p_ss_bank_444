package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.model.BankDetails;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
class BankDetailsMapperTest {

    @Mock
    private BankDetailsMapper bankDetailsMapper;

    @Test
    public void toDtoTest() {
        bankDetailsMapper = Mappers.getMapper(BankDetailsMapper.class);

        BankDetails bankDetails = new BankDetails();
        bankDetails.setId(10L);
        bankDetails.setName("Test bank");

        // Call the mapper to convert the Atm object to a Dto
        BankDetailsDto bankDetailsDto = bankDetailsMapper.toDto(bankDetails);

        // Verify that the conversion was correct
        assertNotNull(bankDetailsDto);
        assertEquals(bankDetails.getId(), bankDetailsDto.getId());
        assertEquals(bankDetails.getName(), bankDetailsDto.getName());
       }

    @Test
    public void toEntityTest() {
        bankDetailsMapper = Mappers.getMapper(BankDetailsMapper.class);
        // Create a sample AtmDto object with known data
        BankDetailsDto bankDetailsDto = new BankDetailsDto();
        bankDetailsDto.setId(10L);
        bankDetailsDto.setName("Test bank");

        // Call the mapper to convert the AtmDto object to an entity
        BankDetails bankDetails = bankDetailsMapper.toEntity(bankDetailsDto);

        // Verify that the conversion was correct
        assertNotNull(bankDetails);
        assertEquals(bankDetailsDto.getId(), bankDetails.getId());
        assertEquals(bankDetailsDto.getName(), bankDetails.getName());

    }
}