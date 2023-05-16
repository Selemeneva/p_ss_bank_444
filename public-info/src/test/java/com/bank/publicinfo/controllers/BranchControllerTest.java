package com.bank.publicinfo.controllers;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.mappers.BranchMapper;
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

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class BranchControllerTest {
    @Mock
    BranchServiceImpl branchService;
    @InjectMocks
    BranchController controller;

    @Test
    void getAllBranchsList() {
        List<BranchDto> expectedList = new ArrayList<>();
        BranchDto branch1 = new BranchDto();
        branch1.setId(1L);
        BranchDto branch2 = new BranchDto();
        branch2.setId(2L);

        expectedList.add(branch1);
        expectedList.add(branch2);
        when(branchService.findAll()).thenReturn(expectedList);

        ResponseEntity<List<BranchDto>> responseEntity = controller.getAllBranchsList();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedList, responseEntity.getBody());
    }

    @Test
    void getBranchById() {
        BranchDto branchDto = new BranchDto();
        when(branchService.findById(1L)).thenReturn(branchDto);
        BranchDto foundBranch = controller.getBranchById(1L).getBody();
        assertThat(foundBranch).isEqualTo(branchDto);
        verify(branchService,times(1)).findById(1L);
    }

    @Test
    void createBranch() {
        BranchDto branchDto = new BranchDto();
        controller.createBranch(branchDto);
        verify(branchService, times(1)).save(branchDto);

    }

    @Test
    void updateBranch() throws JsonProcessingException {
        BranchDto branchDto = new BranchDto();
        controller.updateBranch(branchDto,1L);
        verify(branchService, times(1)).update(branchDto, 1L);
    }

    @Test
    void deleteBranchById() {
        ResponseEntity<HttpStatus> answer = controller.deleteBranchById(1L);
        assertEquals(answer.getStatusCode(), HttpStatus.OK);
    }
}