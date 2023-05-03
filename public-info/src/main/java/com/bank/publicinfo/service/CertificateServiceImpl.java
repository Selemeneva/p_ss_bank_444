package com.bank.publicinfo.service;

import com.bank.publicinfo.model.BankDetails;
import com.bank.publicinfo.model.Certificate;
import com.bank.publicinfo.repositories.CertificateRepository;
import com.bank.publicinfo.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService{
    private final CertificateRepository certificateRepository;

    public CertificateServiceImpl(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }
    @Transactional
    @Override
    public void save(Certificate certificate) {
        certificate.setId(null);
        certificateRepository.save(certificate);
    }
    @Override
    public List<Certificate> findAll() {return certificateRepository.findAll();}
    @Override
    public boolean existById(Long id) {return certificateRepository.existsById(id);}
    @Override
    public Certificate findById(Long id) {return certificateRepository.getReferenceById(id);}
    @Override
    @Transactional
    public void update(Certificate certificate) throws JsonProcessingException {}

    @Transactional
    @Override
    public void update(Certificate certificate, Long id) throws JsonProcessingException {
        Certificate certificateBeforeUpdate = findById(id);
        certificate.setCreatedBy(certificateBeforeUpdate.getCreatedBy());
        certificate.setCreatedAt(certificateBeforeUpdate.getCreatedAt());
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(certificateBeforeUpdate);
        certificateRepository.save(certificate);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {certificateRepository.deleteById(id);}
}
