package com.bank.publicinfo.mappers;


import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.model.License;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = LicenseMapper.class)
public interface LicenseListMapper {
    List<LicenseDto> toDto(List<License> licenseList);
}
