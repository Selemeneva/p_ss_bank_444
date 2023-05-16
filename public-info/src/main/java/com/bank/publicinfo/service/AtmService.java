package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AtmService {
    void save(AtmDto atmDto);
    List<AtmDto> findAll();
    boolean existById(Long id);
    AtmDto findById(Long id);
    void update(AtmDto atmDto, Long id) throws JsonProcessingException;
    void deleteById(Long id);

}
