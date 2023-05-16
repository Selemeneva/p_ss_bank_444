package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.model.Atm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AtmMapper {
    /**
     * Создание экземпляра AtmMapper.
     */

     AtmMapper INSTANCE = Mappers.getMapper(AtmMapper.class);

    @Mapping(target = "branchId", source = "branch.id")
    AtmDto toDto(Atm atm);

    @Mapping(target = "branch.id", source = "branchId")
    Atm toEntity(AtmDto atmDto);


    List<AtmDto> toDto(List<Atm> atmList);
    List<Atm> toEntity(List<AtmDto> atmDto);

}
