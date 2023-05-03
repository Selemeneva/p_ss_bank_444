package com.bank.publicinfo.controllers;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.mappers.CertificateMapper;
import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.model.Certificate;
import com.bank.publicinfo.service.BankDetailsService;
import com.bank.publicinfo.service.CertificateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/bankDetails/certificates")
@Tag(name = "Сертификаты банка", description = "операции с сертификатами")
public class CertificateController {
    private final CertificateService certificateService;
    private final BankDetailsService bankDetailsService;
    private final CertificateMapper certificateMapper;
    private static final Logger logger = LoggerFactory.getLogger(CertificateController.class);

    public CertificateController(CertificateService certificateService, BankDetailsService bankDetailsService,
                                 CertificateMapper certificateMapper) {
        this.certificateService = certificateService;
        this.bankDetailsService = bankDetailsService;
        this.certificateMapper = certificateMapper;
    }

    @GetMapping()
    @Operation(summary = "Получение списка сертификатов", description = "Получение списка сертификатов")
    public ResponseEntity<List<Certificate>> getAllCertificateList() {
        logger.info("Запрос списка всех сертификатов");
        List<Certificate> certificateList = certificateService.findAll();
        return ResponseEntity.ok(certificateList);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение сертификата по id", description = "Получение сертификата по id")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
        logger.info("Запрос сертификата с id:", id);

        if (!certificateService.existById(id)) {
            throw new EntityNotFoundException("Сертификата с таким id не существует");
        }
        return ResponseEntity.ok(certificateService.findById(id));
    }

    @PostMapping()
    @Operation(summary = "Создание Сертификата", description = "Создание Сертификата")
    public ResponseEntity<Certificate> createCertificate(@RequestBody CertificateDto certificateDto) {
        logger.info("Запрос на создание нового Сертификата");
        Long bankDetailsId = certificateDto.getBankDetailsId();

        if (!bankDetailsService.existById(bankDetailsId)) {
            throw new EntityNotFoundException("Сертификата с таким id не существует");
        }
        Certificate certificate = certificateMapper.toEntity(certificateDto, bankDetailsService);
        certificateService.save(certificate);
        return ResponseEntity.ok(certificate);
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление сертификата", description = "Обновление банкомата")
    public ResponseEntity<Certificate> updateCertificate(@RequestBody CertificateDto certificateDto, @PathVariable Long id) throws JsonProcessingException {
        logger.info("Запрос на обновление сертификата");
        Long bankDetailsId = certificateDto.getBankDetailsId();
        Long checkId = certificateDto.getId();

        if (!certificateService.existById(checkId)) {
            throw new EntityNotFoundException("Сертификата с таким id не существует");
        }
        if (!bankDetailsService.existById(bankDetailsId)) {
            throw new EntityNotFoundException("Реквизитов с таким id не существует");
        }
        Certificate certificate = certificateMapper.toEntity(certificateDto, bankDetailsService);
        certificateService.update(certificate, id);

        return ResponseEntity.ok(certificate);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление сертификата по id", description = "Удаление сертификата по id")
    public ResponseEntity<Atm> deleteCertificateById(@PathVariable Long id) {
        logger.info("Запрос на удаление сертификата с id: ", id);

        if (!certificateService.existById(id)) {
            throw new EntityNotFoundException("Сертификата с таким id не существует");
        }
        certificateService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
