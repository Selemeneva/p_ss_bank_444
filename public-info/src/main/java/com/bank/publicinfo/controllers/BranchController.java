package com.bank.publicinfo.controllers;


import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.mappers.BranchMapper;
import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.service.BankDetailsService;
import com.bank.publicinfo.service.BranchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Branch>> getAllBranchsList() {
        logger.info("Запрос списка всех отделений");
        List<Branch> branchList = branchService.findAll();
        return ResponseEntity.ok(branchList);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение отделения по id", description = "Получение отделения по id")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        logger.info("Запрос отделения с id:", id);

        if (!branchService.existById(id)) {
            throw new EntityNotFoundException("Отделения с таким id не существует");
        }
        return ResponseEntity.ok(branchService.findById(id));
    }

    @PostMapping()
    @Operation(summary = "Создание отделения", description = "Создание отделения")
    public ResponseEntity<Branch> createBranch(@RequestBody BranchDto branchDto) {
        logger.info("Запрос на создание нового отделения");
        Long bankDetailsId = branchDto.getBankDetailsId();
        Branch branch = branchMapper.toEntity(branchDto);
        branchService.save(branch);
        return ResponseEntity.ok(branch);
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление инфо отделения", description = "Обновление инфо отделения")
    public ResponseEntity<Branch> updateBranch(@RequestBody BranchDto branchDto, @PathVariable Long id) throws JsonProcessingException {
        logger.info("Запрос на обновление отделения");
        Long checkId = branchDto.getId();

        if (!branchService.existById(checkId)) {
            throw new EntityNotFoundException("Отделения с таким id не существует");
        }
        Branch branch = branchMapper.toEntity(branchDto);
        branchService.update(branch, id);

        return ResponseEntity.ok(branch);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление отделения по id", description = "Удаление отделения по id")
    public ResponseEntity<Branch> deleteBranchById(@PathVariable Long id) {
        logger.info("Запрос на удаление отделения с id: ", id);

        if (!branchService.existById(id)) {
            throw new EntityNotFoundException("Отделения с таким id не существует");
        }
        branchService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
