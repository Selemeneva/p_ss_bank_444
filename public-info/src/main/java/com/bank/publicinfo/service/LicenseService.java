package com.bank.publicinfo.service;

import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.model.License;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface LicenseService {
    void save(License license);
    List<License> findAll();
    boolean existById(Long id);
    License findById(Long id);
    void update(License license) throws JsonProcessingException;
    void update(License license, Long id) throws JsonProcessingException;
    void deleteById(Long id);
}
