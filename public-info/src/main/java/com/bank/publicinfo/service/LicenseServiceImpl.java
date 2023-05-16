package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.mappers.LicenseMapper;
import com.bank.publicinfo.model.License;
import com.bank.publicinfo.repositories.LicenseRepository;
import com.bank.publicinfo.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LicenseServiceImpl implements LicenseService{
    private final LicenseRepository licenseRepository;

    public LicenseServiceImpl(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }
    @Transactional
    @Override
    public void save(LicenseDto licenseDto) {
        licenseDto.setId(null);
        License license = LicenseMapper.INSTANCE.toEntity(licenseDto);
        licenseRepository.save(license);
    }
    @Override
    public List<LicenseDto> findAll() {
        return licenseRepository.findAll().stream()
                .map(LicenseMapper.INSTANCE::toDto)
                .toList();
    }
    @Override
    public boolean existById(Long id) {return licenseRepository.existsById(id);}
    @Override
    public LicenseDto findById(Long id) {
        License license = licenseRepository.getReferenceById(id);
        return LicenseMapper.INSTANCE.toDto(license);
    }
    @Transactional
    @Override
    public void update(LicenseDto licenseDto, Long id) throws JsonProcessingException {
        LicenseDto licenseBeforeUpdate = findById(id);
        License license = LicenseMapper.INSTANCE.toEntity(licenseBeforeUpdate);
        if (license !=null) {
            license.setCreatedBy("Somebody");
            license.setCreatedAt(LocalDateTime.now());
        }
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(license);
        licenseRepository.save(license);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {licenseRepository.deleteById(id);}
}
