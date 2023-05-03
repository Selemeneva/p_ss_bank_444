package com.bank.publicinfo.controllers;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.mappers.BankDetailsMapper;
import com.bank.publicinfo.model.BankDetails;
import com.bank.publicinfo.service.BankDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<List<BankDetails>> getAllBankDetails() {
        logger.info("Запрос списка всех реквизитов");
        List<BankDetails> bankDetailsList = bankDetailsService.findAll();
        return ResponseEntity.ok(bankDetailsList);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение реквизитов по id",
            description = "Получение реквизитов по id")
    public ResponseEntity<BankDetails> getBankDetailsById(@PathVariable Long id) {
        logger.info("Запрос реквизитов по id: ", id);
        if (!bankDetailsService.existById(id)) {
            throw new EntityNotFoundException("Реквизитов с таким id не существует");
        }
        return ResponseEntity.ok(bankDetailsService.findById(id));
    }

    @PostMapping()
    @Operation(summary = "Создание новой записи реквизитов",
            description = "Создание новой записи реквизитов")
    public ResponseEntity<BankDetails> createBankDetails(@RequestBody BankDetailsDto bankDetailsDto) {
        logger.info("Запрос на создание новых реквизитов");
        BankDetails bankDetails = bankDetailsMapper.toEntity(bankDetailsDto);
        bankDetailsService.save(bankDetails);
        return ResponseEntity.ok(bankDetails);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление реквизитов", description = "Обновление реквизитов")
    public ResponseEntity<BankDetails> updateBankDetails(@RequestBody BankDetailsDto bankDetailsDto, @PathVariable Long id)
            throws JsonProcessingException {
        logger.info("Запрос на обновление реквизитов");
        Long checkId = bankDetailsDto.getId();
        if (!bankDetailsService.existById(checkId)) {
            throw new EntityNotFoundException("Реквизитов с таким id не существует");
        }
        BankDetails bankDetails = bankDetailsMapper.toEntity(bankDetailsDto);
        bankDetailsService.update(bankDetails, id);
        return ResponseEntity.ok(bankDetails);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление реквизитов по id", description = "Удаление реквизитов по id")
    public ResponseEntity<BankDetails> deleteBankDetailsById(@PathVariable Long id) {
        logger.info("Запрос на удаление реквизитов с id: ", id);

        if (!bankDetailsService.existById(id)) {
            throw new EntityNotFoundException("Реквизитов с таким id не существует");
        }
        bankDetailsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
