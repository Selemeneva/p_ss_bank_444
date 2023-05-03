package com.bank.publicinfo.service;

import com.bank.publicinfo.model.BankDetails;
import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.repositories.BranchRepository;
import com.bank.publicinfo.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService{

    private final BranchRepository branchRepository;


    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Transactional
    @Override
    public void save(Branch branch) {
        branch.setId(null);
        branchRepository.save(branch);
    }

    @Override
    public List<Branch> findAll() {return branchRepository.findAll();};

    @Override
    public boolean existById(Long id) {return branchRepository.existsById(id);}

    @Override
    public Branch findById(Long id) {return branchRepository.getReferenceById(id);}

    @Transactional
    @Override
    public void update(Branch branch) throws JsonProcessingException {}

    @Transactional
    @Override
    public void update(Branch branch, Long id) throws JsonProcessingException {
        Branch branchBeforeUpdate = findById(id);
        branch.setCreatedBy(branchBeforeUpdate.getCreatedBy());
        branch.setCreatedAt(branchBeforeUpdate.getCreatedAt());
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(branchBeforeUpdate);
        branchRepository.save(branch);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {branchRepository.deleteById(id);}
}
