package com.bank.publicinfo.controllers;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.mappers.CertificateMapper;
import com.bank.publicinfo.service.BankDetailsService;
import com.bank.publicinfo.service.CertificateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<CertificateDto>> getAllCertificateList() {
        logger.info("Запрос списка всех сертификатов");
        List<CertificateDto> certificateList = certificateService.findAll();
        return ResponseEntity.ok(certificateList);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение сертификата по id", description = "Получение сертификата по id")
    public ResponseEntity<CertificateDto> getCertificateById(@PathVariable Long id) {
        logger.info("Запрос сертификата с id:", + id);
        return ResponseEntity.ok(certificateService.findById(id));
    }

    @PostMapping()
    @Operation(summary = "Создание Сертификата", description = "Создание Сертификата")
    public ResponseEntity<CertificateDto> createCertificate(@RequestBody CertificateDto certificateDto) {
        logger.info("Запрос на создание нового Сертификата");
        certificateService.save(certificateDto);
        return ResponseEntity.ok(certificateDto);
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление сертификата", description = "Обновление банкомата")
    public ResponseEntity<CertificateDto> updateCertificate(@RequestBody CertificateDto certificateDto, @PathVariable Long id) throws JsonProcessingException {
        logger.info("Запрос на обновление сертификата");
        certificateService.update(certificateDto, id);
        return ResponseEntity.ok(certificateDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление сертификата по id", description = "Удаление сертификата по id")
    public ResponseEntity<HttpStatus> deleteCertificateById(@PathVariable Long id) {
        logger.info("Запрос на удаление сертификата с id: ", id);
        certificateService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
