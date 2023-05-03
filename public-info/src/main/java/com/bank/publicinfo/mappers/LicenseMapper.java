package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.model.Certificate;
import com.bank.publicinfo.model.License;
import com.bank.publicinfo.service.BankDetailsService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LicenseMapper {
        @Mapping(source = "bankDetails.id", target = "bankDetailsId")
        LicenseDto toDto(License registration);
        @Mapping(target = "bankDetails", expression = "java(bankDetailsService.findById(licenseDto.getBankDetailsId()))")
        License toEntity(LicenseDto licenseDto, @Context BankDetailsService bankDetailsService);
}
