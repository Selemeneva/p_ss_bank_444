package com.bank.publicinfo.mappers;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.model.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CertificateMapper {

    //Создание экземпляра CertificateMapper

    CertificateMapper INSTANCE = Mappers.getMapper(CertificateMapper.class);

    @Mapping(target = "bankDetailsId", source = "bankDetails.id")
    CertificateDto toDto(Certificate certificate);

    @Mapping(target = "bankDetails.id", source = "bankDetailsId")
    Certificate toEntity(CertificateDto certificateDto);

    List<CertificateDto> toDto(List<Certificate> certificateList);
    List<Certificate> toEntity(List<CertificateDto> certificateDtoList);

}


