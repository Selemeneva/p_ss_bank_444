package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.LicenseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface LicenseService {
    void save(LicenseDto licenseDto);
    List<LicenseDto> findAll();
    boolean existById(Long id);
    LicenseDto findById(Long id);
    void update(LicenseDto licenseDto, Long id) throws JsonProcessingException;
    void deleteById(Long id);
}
