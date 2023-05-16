package com.bank.publicinfo.controllers;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.mappers.AtmMapper;
import com.bank.publicinfo.service.AtmService;
import com.bank.publicinfo.service.BranchService;
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
@RequestMapping("/bankDetails/atms")
@Tag(name = "Банкоматы", description = "действия с банкоматами")
public class AtmController {
    private final AtmService atmService;
    private final BranchService branchService;
    private final AtmMapper atmMapper;
    private static final Logger logger = LoggerFactory.getLogger(AtmController.class);

    public AtmController(AtmService atmService, BranchService branchService, AtmMapper atmMapper) {
        this.atmService = atmService;
        this.branchService = branchService;
        this.atmMapper = atmMapper;
    }

    @GetMapping()
    @Operation(summary = "Получение списка банкоматов", description = "Получение списка банкоматов")
    public ResponseEntity<List<AtmDto>> getAllAtmList() {
        logger.info("Запрос списка всех банкоматов");
        List<AtmDto> atmList = atmService.findAll();
        return ResponseEntity.ok(atmList);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение банкомата по id", description = "Получение банкомата по id")
    public ResponseEntity<AtmDto> getAtmById(@PathVariable Long id) {
        logger.info("Запрос банкомата с id:" +id);
        return ResponseEntity.ok(atmService.findById(id));
    }

    @PostMapping()
    @Operation(summary = "Создание банкомата", description = "Создание банкомата")
    public ResponseEntity<AtmDto> createAtm(@RequestBody AtmDto atmDto) {
        logger.info("Запрос на создание нового банкомата");
        Long branchId = atmDto.getBranchId();
        atmService.save(atmDto);
        return ResponseEntity.ok(atmDto);
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление инфо банкомата", description = "Обновление инфо банкомата")
    public ResponseEntity<AtmDto> updateAtm(@RequestBody AtmDto atmDto, @PathVariable Long id) throws JsonProcessingException {
        logger.info("Запрос на обновление инфо банкомата");
        atmService.update(atmDto, id);
        return ResponseEntity.ok(atmDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление банкомата по id", description = "Удаление банкомата по id")
    public ResponseEntity<HttpStatus> deleteAtmById(@PathVariable Long id) {
        logger.info("Запрос на удаление банкомата с id: " + id);
        atmService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
