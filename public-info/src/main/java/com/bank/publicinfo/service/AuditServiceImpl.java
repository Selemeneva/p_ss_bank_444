package com.bank.publicinfo.service;

import com.bank.publicinfo.model.Audit;
import com.bank.publicinfo.repositories.AuditRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService{
    private final AuditRepository auditRepository;


    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }
    @Override
    public void save(Audit audit) {auditRepository.save(audit);}
}
