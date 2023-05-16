package com.bank.publicinfo.controllers;


import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.mappers.LicenseMapper;
import com.bank.publicinfo.service.BankDetailsService;
import com.bank.publicinfo.service.LicenseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankDetails/licenses")
public class LicenseController {
    private final LicenseService licenseService;
    private final BankDetailsService bankDetailsService;
    private final LicenseMapper licenseMapper;
    private static final Logger logger = LoggerFactory.getLogger(LicenseController.class);

    public LicenseController(LicenseService licenseService, BankDetailsService bankDetailsService, LicenseMapper licenseMapper) {
        this.licenseService = licenseService;
        this.bankDetailsService = bankDetailsService;
        this.licenseMapper = licenseMapper;
    }

    @GetMapping()
    @Operation(summary = "Получение списка лицензий", description = "Получение списка лицензий")
    public ResponseEntity<List<LicenseDto>> getAllLicenseList() {
        logger.info("Запрос списка всех лицензий");
        List<LicenseDto> licenseList = licenseService.findAll();
        return ResponseEntity.ok(licenseList);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение лицензии по id", description = "Получение лицензии по id")
    public ResponseEntity<LicenseDto> getLicenseById(@PathVariable Long id) {
        logger.info("Запрос лицензии с id:", id);
        return ResponseEntity.ok(licenseService.findById(id));
    }

    @PostMapping()
    @Operation(summary = "Создание лицензии", description = "Создание лицензии")
    public ResponseEntity<LicenseDto> createLicense(@RequestBody LicenseDto licenseDto) {
        logger.info("Запрос на создание новой лицензии");
        licenseService.save(licenseDto);
        return ResponseEntity.ok(licenseDto);
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление лицензии", description = "Обновление лицензии")
    public ResponseEntity<LicenseDto> updateLicense(@RequestBody LicenseDto licenseDto, @PathVariable Long id) throws JsonProcessingException {
        logger.info("Запрос на обновление лицензии");
        licenseService.update(licenseDto, id);
        return ResponseEntity.ok(licenseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление лицензии по id", description = "Удаление лицензии по id")
    public ResponseEntity<HttpStatus> deleteLicenseById(@PathVariable Long id) {
        logger.info("Запрос на удаление лицензии с id: ", id);
        licenseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
