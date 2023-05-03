package com.bank.publicinfo.service;

import com.bank.publicinfo.model.BankDetails;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BankDetailsService {
    void save(BankDetails bankDetails);
    List<BankDetails> findAll();
    boolean existById(Long id);
    BankDetails findById(Long id);
    void update(BankDetails bankDetails) throws JsonProcessingException;
    void update(BankDetails bankDetails, Long id) throws JsonProcessingException;

    void deleteById(Long id);

}
