package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.mappers.AtmMapper;
import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.repositories.AtmRepository;
import com.bank.publicinfo.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtmServiceImpl implements AtmService{
    private final AtmRepository atmRepository;
    private final BranchServiceImpl branchService;


    public AtmServiceImpl(AtmRepository atmRepository, BranchServiceImpl branchService) {
        this.atmRepository = atmRepository;
        this.branchService = branchService;

    }

    @Transactional
    @Override
    public void save(AtmDto atmDto) {
        atmDto.setId(null);
        Atm atm = AtmMapper.INSTANCE.toEntity(atmDto);
        atmRepository.save(atm);
    }
    @Override
    public List<AtmDto> findAll() {
        return atmRepository.findAll().stream()
            .map(AtmMapper.INSTANCE::toDto)
            .toList();
    }
    @Override
    public boolean existById(Long id) {return atmRepository.existsById(id);}
    @Override
    public AtmDto findById(Long id) {
        Atm atm = atmRepository.getReferenceById(id);
        return AtmMapper.INSTANCE.toDto(atm);
    }

    @Transactional
    @Override
    public void update(AtmDto atmDto, Long id) throws JsonProcessingException {
        AtmDto atmBeforeUpdate = findById(id);
        Atm atm = AtmMapper.INSTANCE.toEntity(atmBeforeUpdate);
        if (atm != null) {
            atm.setCreatedBy("somebody");
            atm.setCreatedAt(LocalDateTime.now());
        }
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(atm);
        atmRepository.save(atm);
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        atmRepository.deleteById(id);
    }
}
