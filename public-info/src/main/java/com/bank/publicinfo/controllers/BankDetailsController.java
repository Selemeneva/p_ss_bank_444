package com.bank.publicinfo.controllers;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.mappers.BankDetailsMapper;
import com.bank.publicinfo.service.BankDetailsService;
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
@Tag(name = "Банковские реквизиты", description = "действия с банковскими реквизитами")
@RequestMapping("/bankDetails")
public class BankDetailsController {
    private final BankDetailsService bankDetailsService;
    private final BankDetailsMapper bankDetailsMapper;
    private static final Logger logger = LoggerFactory.getLogger(BankDetailsController.class);

    public BankDetailsController(BankDetailsService bankDetailsService, BankDetailsMapper bankDetailsMapper) {
        this.bankDetailsService = bankDetailsService;
        this.bankDetailsMapper = bankDetailsMapper;
    }

    @GetMapping()
    @Operation(summary = "Получение всех реквизитов банка",
            description = "Получение всех реквизитов банка")
    public ResponseEntity<List<BankDetailsDto>> getAllBankDetails() {
        logger.info("Запрос списка всех реквизитов");
        List<BankDetailsDto> bankDetailsList = bankDetailsService.findAll();
        return ResponseEntity.ok(bankDetailsList);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение реквизитов по id",
            description = "Получение реквизитов по id")
    public ResponseEntity<BankDetailsDto> getBankDetailsById(@PathVariable Long id) {
        logger.info("Запрос реквизитов по id: " + id);
        return ResponseEntity.ok(bankDetailsService.findById(id));
    }

    @PostMapping()
    @Operation(summary = "Создание новой записи реквизитов",
            description = "Создание новой записи реквизитов")
    public ResponseEntity<BankDetailsDto> createBankDetails(@RequestBody BankDetailsDto bankDetailsDto) {
        logger.info("Запрос на создание новых реквизитов");
        bankDetailsService.save(bankDetailsDto);
        return ResponseEntity.ok(bankDetailsDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление реквизитов", description = "Обновление реквизитов")
    public ResponseEntity<BankDetailsDto> updateBankDetails(@RequestBody BankDetailsDto bankDetailsDto, @PathVariable Long id)
            throws JsonProcessingException {
        logger.info("Запрос на обновление реквизитов");
        bankDetailsService.update(bankDetailsDto, id);
        return ResponseEntity.ok(bankDetailsDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление реквизитов по id", description = "Удаление реквизитов по id")
    public ResponseEntity<HttpStatus> deleteBankDetailsById(@PathVariable Long id) {
        logger.info("Запрос на удаление реквизитов с id: " + id);
        bankDetailsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
