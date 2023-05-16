package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.mappers.BranchMapper;
import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.repositories.BranchRepository;
import com.bank.publicinfo.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService{

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;


    public BranchServiceImpl(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    @Transactional
    @Override
    public void save(BranchDto branchDto) {
        branchDto.setId(null);
        branchRepository.save(BranchMapper.INSTANCE.toEntity(branchDto));
    }

    @Override
    public List<BranchDto> findAll() {
        return branchRepository.findAll().stream()
                .map(BranchMapper.INSTANCE::toDto)
                .toList();};

    @Override
    public boolean existById(Long id) {return branchRepository.existsById(id);}

    @Override
    public BranchDto findById(Long id) {
        Branch branch = branchRepository.getReferenceById(id);
        return BranchMapper.INSTANCE.toDto(branch);
    }

    @Transactional
    @Override
    public void update(BranchDto branchDto, Long id) throws JsonProcessingException {
        BranchDto branchBeforeUpdate = findById(id);
        Branch branch = BranchMapper.INSTANCE.toEntity(branchBeforeUpdate);
        if (branch != null) {
            branch.setCreatedBy("Somebody");
            branch.setCreatedAt(LocalDateTime.now());
        }
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(branch);
        branchRepository.save(branch);

    }

    @Transactional
    @Override
    public void deleteById(Long id) {branchRepository.deleteById(id);}
}
