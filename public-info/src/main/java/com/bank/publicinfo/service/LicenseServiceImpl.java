package com.bank.publicinfo.service;

import com.bank.publicinfo.model.License;
import com.bank.publicinfo.repositories.LicenseRepository;
import com.bank.publicinfo.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LicenseServiceImpl implements LicenseService{
    private final LicenseRepository licenseRepository;

    public LicenseServiceImpl(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }
    @Transactional
    @Override
    public void save(License license) {
        license.setId(null);
        licenseRepository.save(license);
    }
    @Override
    public List<License> findAll() {return licenseRepository.findAll();}
    @Override
    public boolean existById(Long id) {return licenseRepository.existsById(id);}
    @Override
    public License findById(Long id) {return licenseRepository.getReferenceById(id); }
    @Transactional
    @Override
    public void update(License license) throws JsonProcessingException {}

    @Transactional
    @Override
    public void update(License license, Long id) throws JsonProcessingException {
        License licenseBeforeUpdate = findById(id);
        license.setCreatedBy(licenseBeforeUpdate.getCreatedBy());
        license.setCreatedAt(licenseBeforeUpdate.getCreatedAt());
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(licenseBeforeUpdate);
        licenseRepository.save(license);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {licenseRepository.deleteById(id);}
}
