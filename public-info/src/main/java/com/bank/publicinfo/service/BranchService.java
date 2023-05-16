package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BranchDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BranchService {
    void save(BranchDto branchDto);
    List<BranchDto> findAll();
    boolean existById(Long id);
    BranchDto findById(Long id);
    void update(BranchDto branchDto, Long id) throws JsonProcessingException;
    void deleteById(Long id);


}
