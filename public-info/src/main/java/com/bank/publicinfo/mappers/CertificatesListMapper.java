package com.bank.publicinfo.mappers;


import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.model.Certificate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CertificateMapper.class)
public interface CertificatesListMapper {
    List<CertificateDto> toDto(List<Certificate> certificateList);
}
