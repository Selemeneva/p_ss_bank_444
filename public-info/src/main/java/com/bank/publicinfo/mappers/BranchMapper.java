package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.service.BankDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BranchMapper.class)
public interface BranchMapper {

    BranchDto toDto(Branch branch);

    Branch toEntity(BranchDto branchDto);

}
