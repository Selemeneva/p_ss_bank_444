package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.model.Branch;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
class BranchMapperTest {

    @Mock
    private BranchMapper branchMapper;

    @Test
    public void toDtoTest() {
        branchMapper = Mappers.getMapper(BranchMapper.class);

        Branch branch = new Branch();
        branch.setId(10L);
        branch.setCity("Test branch");

        // Call the mapper to convert the Branch object to a Dto
        BranchDto branchDto = branchMapper.toDto(branch);

        // Verify that the conversion was correct
        assertNotNull(branchDto);
        assertEquals(branch.getId(), branchDto.getId());
        assertEquals(branch.getCity(), branchDto.getCity());
    }

    @Test
    public void toEntityTest() {
        branchMapper = Mappers.getMapper(BranchMapper.class);
        // Create a sample BranchDto object with known data
        BranchDto branchDto = new BranchDto();
        branchDto.setId(10L);
        branchDto.setCity("Test branch");

        // Call the mapper to convert the BranchDto object to an entity
        Branch branch = branchMapper.toEntity(branchDto);

        // Verify that the conversion was correct
        assertNotNull(branch);
        assertEquals(branchDto.getId(), branch.getId());
        assertEquals(branchDto.getCity(), branch.getCity());

    }
}