package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.model.Atm;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring", uses = AtmMapper.class)
public interface AtmListMapper {
    List<AtmDto> toDto(List<Atm> atmList);
}
