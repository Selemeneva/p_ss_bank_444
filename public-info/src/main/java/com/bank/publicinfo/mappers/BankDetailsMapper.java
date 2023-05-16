package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.model.BankDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BankDetailsMapper {

   // Создание экземпляра BankDetailsMapper

   BankDetailsMapper INSTANCE = Mappers.getMapper(BankDetailsMapper.class);
   BankDetailsDto toDto(BankDetails bankDetails);
   BankDetails toEntity(BankDetailsDto bankDetailsDto);
}
