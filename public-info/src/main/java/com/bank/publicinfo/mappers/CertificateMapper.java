package com.bank.publicinfo.mappers;


import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.model.Certificate;
import com.bank.publicinfo.service.BankDetailsService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CertificateMapper.class)
public interface CertificateMapper {
    @Mapping(source = "bankDetails.id", target = "bankDetailsId")
    CertificateDto toDto(Certificate certificate);
    @Mapping(target = "bankDetails", expression = "java(bankDetailsService.findById(certificateDto.getBankDetailsId()))")
    Certificate toEntity(CertificateDto certificateDto, @Context BankDetailsService bankDetailsService);
}

