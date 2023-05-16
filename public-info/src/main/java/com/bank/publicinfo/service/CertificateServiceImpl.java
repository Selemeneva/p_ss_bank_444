package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.mappers.CertificateMapper;
import com.bank.publicinfo.model.Certificate;
import com.bank.publicinfo.repositories.CertificateRepository;
import com.bank.publicinfo.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService{
    private final CertificateRepository certificateRepository;
    private final BankDetailsServiceImpl bankDetailsService;

    public CertificateServiceImpl(CertificateRepository certificateRepository, BankDetailsServiceImpl bankDetailsService) {
        this.certificateRepository = certificateRepository;
        this.bankDetailsService = bankDetailsService;
    }
    @Transactional
    @Override
    public void save(CertificateDto certificateDto) {
        certificateDto.setId(null);
        Certificate certificate = CertificateMapper.INSTANCE.toEntity(certificateDto);
        certificateRepository.save(certificate);
    }
    @Override
    public List<CertificateDto> findAll() {
        return certificateRepository.findAll().stream()
                .map(CertificateMapper.INSTANCE::toDto)
                .toList();
    }
    @Override
    public boolean existById(Long id) {return certificateRepository.existsById(id);}
    @Override
    public CertificateDto findById(Long id) {
        Certificate certificate = certificateRepository.getReferenceById(id);
        return CertificateMapper.INSTANCE.toDto(certificate);
    }
    @Transactional
    @Override
    public void update(CertificateDto certificateDto, Long id) throws JsonProcessingException {
        CertificateDto certificateBeforeUpdate = findById(id);
        Certificate certificate = CertificateMapper.INSTANCE.toEntity(certificateBeforeUpdate);
        if (certificate != null) {
            certificate.setCreatedBy("Somebody");
            certificate.setCreatedAt(LocalDateTime.now());
        }
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(certificate);
        certificateRepository.save(certificate);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {certificateRepository.deleteById(id);}
}
