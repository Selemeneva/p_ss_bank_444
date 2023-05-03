package com.bank.publicinfo.controllers;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.mappers.AtmMapper;
import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.service.AtmService;
import com.bank.publicinfo.service.BranchService;
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
    public ResponseEntity<List<Atm>> getAllAtmList() {
        logger.info("Запрос списка всех банкоматов");
        List<Atm> atmList = atmService.findAll();
        return ResponseEntity.ok(atmList);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение банкомата по id", description = "Получение банкомата по id")
    public ResponseEntity<Atm> getAtmById(@PathVariable Long id) {
        logger.info("Запрос банкомата с id:", id);

        if (!atmService.existById(id)) {
            throw new EntityNotFoundException("Банкомата с таким id не существует");
        }
        return ResponseEntity.ok(atmService.findById(id));
    }

    @PostMapping()
    @Operation(summary = "Создание банкомата", description = "Создание банкомата")
    public ResponseEntity<Atm> createAtm(@RequestBody AtmDto atmDto) {
        logger.info("Запрос на создание нового банкомата");
        Long branchId = atmDto.getBranchId();

        if (!branchService.existById(branchId)) {
            throw new EntityNotFoundException("Отделения с таким id не существует");
        }
        Atm atm = atmMapper.toEntity(atmDto, branchService);
        atmService.save(atm);
        return ResponseEntity.ok(atm);
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление инфо банкомата", description = "Обновление инфо банкомата")
    public ResponseEntity<Atm> updateAtm(@RequestBody AtmDto atmDto, @PathVariable Long id) throws JsonProcessingException {
        logger.info("Запрос на обновление инфо банкомата");
       Long branchId = atmDto.getBranchId();
       Long checkId = atmDto.getId();

        if (!atmService.existById(checkId)) {
            throw new EntityNotFoundException("Банкомата с таким id не существует");
        }
        if (!branchService.existById(branchId)) {
            throw new EntityNotFoundException("Отделения с таким id не существует");
        }
        Atm atm = atmMapper.toEntity(atmDto, branchService);
        atmService.update(atm, id);

        return ResponseEntity.ok(atm);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление банкомата по id", description = "Удаление банкомата по id")
    public ResponseEntity<Atm> deleteAtmById(@PathVariable Long id) {
        logger.info("Запрос на удаление банкомата с id: ", id);

        if (!atmService.existById(id)) {
            throw new EntityNotFoundException("Банкомата с таким id не существует");
        }
        atmService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
