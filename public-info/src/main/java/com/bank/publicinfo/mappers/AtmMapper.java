package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.service.BankDetailsService;
import com.bank.publicinfo.service.BranchService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AtmMapper.class)
public interface AtmMapper {
    @Mapping(target = "branch", expression = "java(branchService.findById(atmDto.getBranchId()))")
    Atm toEntity(AtmDto atmDto, @Context BranchService branchService);

    @Mapping(source = "branch.id", target = "branchId")
    AtmDto toDto(Atm atm);
}
