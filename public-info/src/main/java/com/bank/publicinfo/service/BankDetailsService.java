package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BankDetailsService {
    void save(BankDetailsDto bankDetailsDto);
    List<BankDetailsDto> findAll();
    boolean existById(Long id);
    BankDetailsDto findById(Long id);

    void update(BankDetailsDto bankDetailsDto, Long id) throws JsonProcessingException;

    void deleteById(Long id);

}
