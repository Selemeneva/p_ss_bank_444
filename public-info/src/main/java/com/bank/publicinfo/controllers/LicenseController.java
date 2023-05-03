package com.bank.publicinfo.controllers;


import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.mappers.LicenseMapper;
import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.model.License;
import com.bank.publicinfo.service.BankDetailsService;
import com.bank.publicinfo.service.LicenseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<List<License>> getAllLicenseList() {
        logger.info("Запрос списка всех лицензий");
        List<License> licenseList = licenseService.findAll();
        return ResponseEntity.ok(licenseList);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение лицензии по id", description = "Получение лицензии по id")
    public ResponseEntity<License> getLicenseById(@PathVariable Long id) {
        logger.info("Запрос лицензии с id:", id);

        if (!licenseService.existById(id)) {
            throw new EntityNotFoundException("Лицензии с таким id не существует");
        }
        return ResponseEntity.ok(licenseService.findById(id));
    }

    @PostMapping()
    @Operation(summary = "Создание лицензии", description = "Создание лицензии")
    public ResponseEntity<License> createLicense(@RequestBody LicenseDto licenseDto) {
        logger.info("Запрос на создание новой лицензии");
        Long bankDetailsId = licenseDto.getBankDetailsId();

        if (!bankDetailsService.existById(bankDetailsId)) {
            throw new EntityNotFoundException("Реквизитов с таким id не существует");
        }
        License license = licenseMapper.toEntity(licenseDto, bankDetailsService);
        licenseService.save(license);
        return ResponseEntity.ok(license);
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление лицензии", description = "Обновление лицензии")
    public ResponseEntity<License> updateLicense(@RequestBody LicenseDto licenseDto, @PathVariable Long id) throws JsonProcessingException {
        logger.info("Запрос на обновление лицензии");
        Long bankDetailsId = licenseDto.getBankDetailsId();
        Long checkId = licenseDto.getId();

        if (!licenseService.existById(checkId)) {
            throw new EntityNotFoundException("Лицензии с таким id не существует");
        }
        if (!bankDetailsService.existById(bankDetailsId)) {
            throw new EntityNotFoundException("Реквизитов с таким id не существует");
        }
        License license = licenseMapper.toEntity(licenseDto, bankDetailsService);
        licenseService.update(license, id);

        return ResponseEntity.ok(license);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление лицензии по id", description = "Удаление лицензии по id")
    public ResponseEntity<Atm> deleteLicenseById(@PathVariable Long id) {
        logger.info("Запрос на удаление лицензии с id: ", id);

        if (!licenseService.existById(id)) {
            throw new EntityNotFoundException("Лицензии с таким id не существует");
        }
        licenseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
