package com.bank.publicinfo.service;

import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.model.Certificate;
import com.fasterxml.jackson.core.JsonProcessingException;
import liquibase.pro.packaged.C;

import java.util.List;

public interface CertificateService {
    void save(Certificate certificate);
    List<Certificate> findAll();
    boolean existById(Long id);
    Certificate findById(Long id);
    void update(Certificate certificate) throws JsonProcessingException;
    void update(Certificate certificate, Long id) throws JsonProcessingException;
    void deleteById(Long id);
}
