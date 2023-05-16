package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = BranchMapper.class)
public interface BranchMapper {

    //Создание экземпляра BranchMapper

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    BranchDto toDto(Branch branch);

    Branch toEntity(BranchDto branchDto);


}
