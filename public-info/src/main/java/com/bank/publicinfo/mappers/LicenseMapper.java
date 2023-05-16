package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.model.License;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LicenseMapper {

    //Создание экземпляра LicenseMapper

    LicenseMapper INSTANCE = Mappers.getMapper(LicenseMapper.class);

    @Mapping(target = "bankDetailsId", source = "bankDetails.id")
    LicenseDto toDto(License license);

    @Mapping(target = "bankDetails.id", source = "bankDetailsId")
    License toEntity(LicenseDto licenseDto);

    List<LicenseDto> toDto(List<License> licenseList);
    List<License> toEntity(List<LicenseDto> licenseDtoList);

}
