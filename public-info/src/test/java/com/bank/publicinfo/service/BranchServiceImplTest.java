package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.mappers.BranchMapper;
import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.repositories.BranchRepository;
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

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class BranchServiceImplTest {

    @InjectMocks
    BranchServiceImpl branchService;

    @Mock
    BranchRepository branchRepository;

    Branch branch;
    BranchDto branchDto;


    @BeforeEach
    void setUp() {
        branchDto = BranchDto.builder()
                .id(1L)
                .address("Baker Street, 10")
                .city("London")
                .phoneNumber(777L)
                .startOfWork(LocalTime.parse("09:00:00"))
                .endOfWork(LocalTime.parse("20:00:00"))
                .build();
        branch = Branch.builder()
                .id(1L)
                .address("Baker Street, 10")
                .city("London")
                .phoneNumber(777L)
                .startOfWork(LocalTime.parse("09:00:00"))
                .endOfWork(LocalTime.parse("20:00:00"))
                .build();
        branch = BranchMapper.INSTANCE.toEntity(branchDto);

    }

    @Test
    void save() {
        branchService.save(BranchMapper.INSTANCE.toDto(branch));
        /**
         *ArgumentCaptor - утилитный класс, для "захвата" полей класса
         **/
        ArgumentCaptor<Branch> branchCaptor = ArgumentCaptor.forClass(Branch.class);
        Mockito.verify(branchRepository).save(branchCaptor.capture());
        Branch savedBranch = branchCaptor.getValue();
        assertNull(savedBranch.getId());
        assertEquals(savedBranch.getAddress(), branchDto.getAddress());
        assertEquals(savedBranch.getPhoneNumber(), branchDto.getPhoneNumber());
        assertEquals(savedBranch.getCity(), branchDto.getCity());
        assertEquals(savedBranch.getEndOfWork(), branchDto.getEndOfWork());
        assertEquals(savedBranch.getStartOfWork(), branchDto.getStartOfWork());
    }

    @Test
    void findAll() {
        // Given
        List<Branch> branchList = new ArrayList<>();
        branchList.add(branch);
        Mockito.when(branchRepository.findAll()).thenReturn(branchList);
        // When
        List<BranchDto> foundDtoList = branchService.findAll();
        // Then
        Mockito.verify(branchRepository, times(1)).findAll();
        assertEquals(foundDtoList.size(), branchList.size());
        for (int i = 0; i < foundDtoList.size(); i++) {
            Branch foundBankDetails = BranchMapper.INSTANCE.toEntity(foundDtoList.get(i));
            assertEquals(foundBankDetails.getId(), branchList.get(i).getId());
            assertEquals(foundBankDetails.getCity(), branchList.get(i).getCity());
            assertEquals(foundBankDetails.getPhoneNumber(), branchList.get(i).getPhoneNumber());
            assertEquals(foundBankDetails.getAddress(), branchList.get(i).getAddress());
            assertEquals(foundBankDetails.getStartOfWork(), branchList.get(i).getStartOfWork());
            assertEquals(foundBankDetails.getEndOfWork(), branchList.get(i).getEndOfWork());
        }
    }

    @Test
    void existById() {
        when(branchRepository.existsById(1L)).thenReturn(true);
        Boolean actual = branchService.existById(1L);
        assertEquals(true, actual);
    }

    @Test
    void findById() {
        Mockito.when(branchRepository.getReferenceById(1L)).thenReturn(branch);
        // When
        BranchDto foundDto = branchService.findById(1L);
        // Then
        Mockito.verify(branchRepository, times(1)).getReferenceById(1L);
        assertEquals(foundDto.getId(), branch.getId());
    }

    @Test
    void update() throws JsonProcessingException {
        Long id = branchDto.getId();
        branchDto.setCity("London");
        branchService.update(branchDto, id);
        assertEquals(branchDto.getCity(), "London");
    }

    @Test
    void deleteById() {
        long id = 1L;
        branchService.deleteById(id);
        verify(branchRepository).deleteById(id);
    }
}