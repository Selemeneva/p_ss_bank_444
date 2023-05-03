package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.model.Branch;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = BranchMapper.class)
public interface BranchListMapper {
    List<BranchDto> toDto(List<Branch> branchList);

}
