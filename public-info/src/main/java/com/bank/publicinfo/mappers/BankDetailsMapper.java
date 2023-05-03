package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.model.BankDetails;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BankDetailsMapper.class)
public interface BankDetailsMapper {
   BankDetailsDto toDto(BankDetails bankDetails);
   BankDetails toEntity(BankDetailsDto bankDetailsDto);
}
