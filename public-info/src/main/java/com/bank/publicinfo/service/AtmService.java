package com.bank.publicinfo.service;

import com.bank.publicinfo.model.Atm;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface AtmService {
    void save(Atm atm);
    List<Atm> findAll();
    boolean existById(Long id);
    Atm findById(Long id);
    void update(Atm atm) throws JsonProcessingException;
    void update(Atm atm, Long id) throws JsonProcessingException;
    void deleteById(Long id);

}
