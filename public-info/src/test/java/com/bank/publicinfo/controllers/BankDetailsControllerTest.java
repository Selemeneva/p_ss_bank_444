package com.bank.publicinfo.controllers;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.mappers.BankDetailsMapper;
import com.bank.publicinfo.service.BankDetailsServiceImpl;
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
class BankDetailsControllerTest {
    @Mock
    BankDetailsServiceImpl bankDetailsService;
    @InjectMocks
    BankDetailsController controller;

    @Test
    void getAllBankDetails() {
        List<BankDetailsDto> expectedList = new ArrayList<>();
        BankDetailsDto bankDetails1 = new BankDetailsDto();
        bankDetails1.setId(1L);
        BankDetailsDto bankDetails2 = new BankDetailsDto();
        bankDetails2.setId(2L);

        expectedList.add(bankDetails1);
        expectedList.add(bankDetails2);
        when(bankDetailsService.findAll()).thenReturn(expectedList);

        ResponseEntity<List<BankDetailsDto>> responseEntity = controller.getAllBankDetails();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedList, responseEntity.getBody());
    }


    @Test
    void getBankDetailsById() {
        BankDetailsDto bankDetailsDto = new BankDetailsDto();
        when(bankDetailsService.findById(1L)).thenReturn(bankDetailsDto);
        BankDetailsDto foundBankDetails = controller.getBankDetailsById(1L).getBody();
        assertThat(foundBankDetails).isEqualTo(bankDetailsDto);
        verify(bankDetailsService,times(1)).findById(1L);
    }

    @Test
    void createBankDetails() {
        BankDetailsDto bankDetailsDto = new BankDetailsDto();
        controller.createBankDetails(bankDetailsDto);
        verify(bankDetailsService, times(1)).save(bankDetailsDto);

    }

    @Test
    void updateBankDetails() throws JsonProcessingException {
        BankDetailsDto bankDetailsDto = new BankDetailsDto();
        controller.updateBankDetails(bankDetailsDto,1L);
        verify(bankDetailsService, times(1)).update(bankDetailsDto, 1L);
    }

    @Test
    void deleteBankDetailsById() {
        ResponseEntity<HttpStatus> answer = controller.deleteBankDetailsById(1L);
        assertEquals(answer.getStatusCode(), HttpStatus.OK);
    }
}