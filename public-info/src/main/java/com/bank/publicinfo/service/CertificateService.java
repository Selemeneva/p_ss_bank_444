package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.CertificateDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CertificateService {
    void save(CertificateDto certificateDto);
    List<CertificateDto> findAll();
    boolean existById(Long id);
    CertificateDto findById(Long id);
    void update(CertificateDto certificateDto, Long id) throws JsonProcessingException;
    void deleteById(Long id);
}
