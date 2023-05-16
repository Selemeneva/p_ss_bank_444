package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.mappers.BankDetailsMapper;
import com.bank.publicinfo.model.BankDetails;
import com.bank.publicinfo.repositories.BankDetailsRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class BankDetailsServiceImplTest {

    @Mock
    BankDetailsRepository bankDetailsRepository;

    @InjectMocks
    BankDetailsServiceImpl bankDetailsService;
    BankDetailsDto bankDetailsDto;
    BankDetails bankDetails;

    @BeforeEach
    public void setUp() {
        bankDetailsDto = BankDetailsDto.builder()
                .id(1L)
                .bik(111L)
                .kpp(222L)
                .city("Moscow")
                .corAccount(333)
                .jointStockCompany("ZAO KB")
                .name("Capital")
                .inn(444L)
                .build();
        bankDetails = BankDetails.builder()
                .id(1L)
                .bik(111L)
                .kpp(222L)
                .city("Moscow")
                .corAccount(333)
                .jointStockCompany("ZAO KB")
                .name("Capital")
                .inn(444L)
                .build();
        bankDetails = BankDetailsMapper.INSTANCE.toEntity(bankDetailsDto);
       }
    @Test
    void save() {
        bankDetailsService.save(BankDetailsMapper.INSTANCE.toDto(bankDetails));
        /**
         *ArgumentCaptor - утилитный класс, для "захвата" полей класса
         **/
        ArgumentCaptor<BankDetails> bankDetailsCaptor = ArgumentCaptor.forClass(BankDetails.class);
        Mockito.verify(bankDetailsRepository).save(bankDetailsCaptor.capture());
        BankDetails savedBankDetails = bankDetailsCaptor.getValue();
        assertNull(savedBankDetails.getId());
        assertEquals(savedBankDetails.getBik(), bankDetailsDto.getBik());
        assertEquals(savedBankDetails.getInn(), bankDetailsDto.getInn());
        assertEquals(savedBankDetails.getKpp(), bankDetailsDto.getKpp());
        assertEquals(savedBankDetails.getCorAccount(), bankDetailsDto.getCorAccount());
        assertEquals(savedBankDetails.getName(), bankDetailsDto.getName());
        assertEquals(savedBankDetails.getCity(), bankDetailsDto.getCity());
        assertEquals(savedBankDetails.getJointStockCompany(), bankDetailsDto.getJointStockCompany());
    }

    @Test
    void findAll() {
        // Given
        List<BankDetails> bankDetailsList = new ArrayList<>();
        bankDetailsList.add(bankDetails);
        Mockito.when(bankDetailsRepository.findAll()).thenReturn(bankDetailsList);
        // When
        List<BankDetailsDto> foundDtoList = bankDetailsService.findAll();
        // Then
        Mockito.verify(bankDetailsRepository, times(1)).findAll();
        assertEquals(foundDtoList.size(), bankDetailsList.size());
        for (int i = 0; i < foundDtoList.size(); i++) {
            BankDetails foundBankDetails = BankDetailsMapper.INSTANCE.toEntity(foundDtoList.get(i));
            assertEquals(foundBankDetails.getId(), bankDetailsList.get(i).getId());
            assertEquals(foundBankDetails.getInn(), bankDetailsList.get(i).getInn());
            assertEquals(foundBankDetails.getKpp(), bankDetailsList.get(i).getKpp());
            assertEquals(foundBankDetails.getBik(), bankDetailsList.get(i).getBik());
            assertEquals(foundBankDetails.getCorAccount(), bankDetailsList.get(i).getCorAccount());
            assertEquals(foundBankDetails.getName(), bankDetailsList.get(i).getName());
            assertEquals(foundBankDetails.getJointStockCompany(), bankDetailsList.get(i).getJointStockCompany());
        }
    }

    @Test
    void existById() {
        when(bankDetailsRepository.existsById(1L)).thenReturn(true);
        Boolean actual = bankDetailsService.existById(1L);
        assertEquals(true, actual);
    }

    @Test
    void findById() {
        Mockito.when(bankDetailsRepository.getReferenceById(1L)).thenReturn(bankDetails);
        // When
        BankDetailsDto foundDto = bankDetailsService.findById(1L);
        // Then
        Mockito.verify(bankDetailsRepository, times(1)).getReferenceById(1L);
        assertEquals(foundDto.getId(), bankDetails.getId());
    }

    @Test
    void update() throws JsonProcessingException {
        Long id = bankDetailsDto.getId();
        bankDetailsDto.setCity("London");
        bankDetailsService.update(bankDetailsDto, id);
        assertEquals(bankDetailsDto.getCity(), "London");
    }

    @Test
    void deleteById() {
        long id = 1L;
        bankDetailsService.deleteById(id);
        verify(bankDetailsRepository).deleteById(id);
    }
}