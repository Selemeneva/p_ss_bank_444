package com.bank.publicinfo.service;

import com.bank.publicinfo.model.BankDetails;
import com.bank.publicinfo.model.Branch;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BranchService {
    void save(Branch branch);
    List<Branch> findAll();
    boolean existById(Long id);
    Branch findById(Long id);
    void update(Branch branch) throws JsonProcessingException;
    void update(Branch branch, Long id) throws JsonProcessingException;
    void deleteById(Long id);


}
