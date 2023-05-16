package com.bank.publicinfo.controllers;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.mappers.AtmMapper;
import com.bank.publicinfo.service.AtmServiceImpl;
import com.bank.publicinfo.service.BranchServiceImpl;
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


/*
Проверяем, что контроллер возвращает нужные статусы и ответы
 */
@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class AtmControllerTest {
    @Mock
    AtmServiceImpl atmService;
    @Mock
    BranchServiceImpl branchService;
    @InjectMocks
    AtmController controller;
    @Test
    void deleteAtmById() {
    ResponseEntity<HttpStatus> answer = controller.deleteAtmById(1L);
    assertEquals(answer.getStatusCode(), HttpStatus.OK);

    }
    @Test
    void createAtm() {
        AtmDto atmDto = new AtmDto();
        controller.createAtm(atmDto);
        verify(atmService, times(1)).save(atmDto);
    }

    @Test
    void getAtmById() {
        AtmDto atmDto = new AtmDto();
        when(atmService.findById(1L)).thenReturn(atmDto);
        AtmDto foundAtm = controller.getAtmById(1L).getBody();
        assertThat(foundAtm).isEqualTo(atmDto);
        verify(atmService,times(1)).findById(1L);
    }

    @Test
    void getAllAtmList() {
        List<AtmDto> expectedList = new ArrayList<>();
        AtmDto atm1 = new AtmDto();
        atm1.setId(1L);
        AtmDto atm2 = new AtmDto();
        atm2.setId(2L);

        expectedList.add(atm1);
        expectedList.add(atm2);
        when(atmService.findAll()).thenReturn(expectedList);

        ResponseEntity<List<AtmDto>> responseEntity = controller.getAllAtmList();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedList, responseEntity.getBody());
    }

    @Test
    void updateAtm() throws JsonProcessingException {
        AtmDto atmDto = new AtmDto();
        controller.updateAtm(atmDto,1L);
        verify(atmService, times(1)).update(atmDto, 1L);
    }
}