package com.bank.publicinfo.controllers;


import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.mappers.BranchMapper;
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
@RequestMapping("/bankDetails/branches")
@Tag(name = "Отделения банка", description = "операции с отделениями банка")
public class BranchController {
    private final BranchService branchService;
    private final BranchMapper branchMapper;
    private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

    public BranchController(BranchService branchService, BranchMapper branchMapper) {
        this.branchService = branchService;
        this.branchMapper = branchMapper;
    }

    @GetMapping()
    @Operation(summary = "Получение списка отделений", description = "Получение списка отделений")
    public ResponseEntity<List<BranchDto>> getAllBranchsList() {
        logger.info("Запрос списка всех отделений");
        List<BranchDto> branchList = branchService.findAll();
        return ResponseEntity.ok(branchList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение отделения по id", description = "Получение отделения по id")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) {
        logger.info("Запрос отделения с id:" + id);
        return ResponseEntity.ok(branchService.findById(id));
    }

    @PostMapping()
    @Operation(summary = "Создание отделения", description = "Создание отделения")
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) {
        logger.info("Запрос на создание нового отделения");
        branchService.save(branchDto);
        return ResponseEntity.ok(branchDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление инфо отделения", description = "Обновление инфо отделения")
    public ResponseEntity<BranchDto> updateBranch(@RequestBody BranchDto branchDto, @PathVariable Long id) throws JsonProcessingException {
        logger.info("Запрос на обновление отделения");
        branchService.update(branchDto, id);
        return ResponseEntity.ok(branchDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление отделения по id", description = "Удаление отделения по id")
    public ResponseEntity<HttpStatus> deleteBranchById(@PathVariable Long id) {
        logger.info("Запрос на удаление отделения с id: " + id);
        branchService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
