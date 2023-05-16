package com.bank.publicinfo.service;

import com.bank.publicinfo.model.Audit;
import com.bank.publicinfo.repositories.AuditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class AuditServiceImplTest {

    @Mock
    private AuditRepository auditRepository;

    @InjectMocks
    private AuditServiceImpl auditService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        Audit audit = new Audit();
        auditService.save(audit);
        verify(auditRepository).save(audit);
    }
}