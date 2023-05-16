package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.model.Branch;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
class AtmMapperTest {
    @Mock
    private AtmMapper atmMapper;

    @Test
    public void toDtoTest() {
        atmMapper = Mappers.getMapper(AtmMapper.class);
        // Create a sample Atm object with known data
        Branch branch = new Branch();
        branch.setId(1L);

        Atm atm = new Atm();
        atm.setId(10L);
        atm.setAddress("Test ATM");
        atm.setBranch(branch);

        // Call the mapper to convert the Atm object to a Dto
        AtmDto atmDto = atmMapper.toDto(atm);

        // Verify that the conversion was correct
        assertNotNull(atmDto);
        assertEquals(atm.getId(), atmDto.getId());
        assertEquals(atm.getAddress(), atmDto.getAddress());
        assertEquals(atm.getBranch().getId(), atmDto.getBranchId());
    }

    @Test
    public void toEntityTest() {
        atmMapper = Mappers.getMapper(AtmMapper.class);
        // Create a sample AtmDto object with known data
        AtmDto atmDto = new AtmDto();
        atmDto.setId(10L);
        atmDto.setAddress("Test ATM");
        atmDto.setBranchId(1L);

        // Call the mapper to convert the AtmDto object to an entity
        Atm atm = atmMapper.toEntity(atmDto);

        // Verify that the conversion was correct
        assertNotNull(atm);
        assertEquals(atmDto.getId(), atm.getId());
        assertEquals(atmDto.getAddress(), atm.getAddress());
        assertEquals(atmDto.getBranchId(), atm.getBranch().getId());
    }

    @Test
    public void toDtoListTest() {
        atmMapper = Mappers.getMapper(AtmMapper.class);
        // Create a list of sample Atm objects with known data
        Branch branch1 = new Branch();
        branch1.setId(1L);

        Atm atm1 = new Atm();
        atm1.setId(10L);
        atm1.setAddress("Test ATM 1");
        atm1.setBranch(branch1);

        Branch branch2 = new Branch();
        branch2.setId(2L);

        Atm atm2 = new Atm();
        atm2.setId(20L);
        atm2.setAddress("Test ATM 2");
        atm2.setBranch(branch2);

        List<Atm> atmList = new ArrayList<>();
        atmList.add(atm1);
        atmList.add(atm2);

        // Call the mapper to convert the list of Atm objects to a list of Dtos
        List<AtmDto> atmDtoList = atmMapper.toDto(atmList);

        // Verify that the conversion was correct
        assertNotNull(atmDtoList);
        assertEquals(atmList.size(), atmDtoList.size());
        assertEquals(atm1.getId(), atmDtoList.get(0).getId());
        assertEquals(atm1.getAddress(), atmDtoList.get(0).getAddress());
        assertEquals(atm1.getBranch().getId(), atmDtoList.get(0).getBranchId());
        assertEquals(atm2.getId(), atmDtoList.get(1).getId());
        assertEquals(atm2.getAddress(), atmDtoList.get(1).getAddress());
        assertEquals(atm2.getBranch().getId(), atmDtoList.get(1).getBranchId());
    }

    @Test
    public void toEntityListTest() {
        atmMapper = Mappers.getMapper(AtmMapper.class);
        // Create a list of sample AtmDto objects with known data
        AtmDto atmDto1 = new AtmDto();
        atmDto1.setId(10L);
        atmDto1.setAddress("Test ATM 1");
        atmDto1.setBranchId(1L);

        AtmDto atmDto2 = new AtmDto();
        atmDto2.setId(20L);
        atmDto2.setAddress("Test ATM 2");
        atmDto2.setBranchId(2L);

        List<AtmDto> atmDtoList = new ArrayList<>();
        atmDtoList.add(atmDto1);
        atmDtoList.add(atmDto2);

        // Call the mapper to convert the list of AtmDto objects to a list of entities
        List<Atm> atmList = atmMapper.toEntity(atmDtoList);

        // Verify that the conversion was correct
        assertNotNull(atmList);
        assertEquals(atmDtoList.size(), atmList.size());
        assertEquals(atmDto1.getId(), atmList.get(0).getId());
        assertEquals(atmDto1.getAddress(), atmList.get(0).getAddress());
        assertEquals(atmDto1.getBranchId(), atmList.get(0).getBranch().getId());
        assertEquals(atmDto2.getId(), atmList.get(1).getId());
        assertEquals(atmDto2.getAddress(), atmList.get(1).getAddress());
        assertEquals(atmDto2.getBranchId(), atmList.get(1).getBranch().getId());

    }
}