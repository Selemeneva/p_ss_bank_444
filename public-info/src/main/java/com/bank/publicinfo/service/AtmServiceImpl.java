package com.bank.publicinfo.service;

import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.repositories.AtmRepository;
import com.bank.publicinfo.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AtmServiceImpl implements AtmService{
    private final AtmRepository atmRepository;


    public AtmServiceImpl(AtmRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    @Transactional
    @Override
    public void save(Atm atm) {
        atm.setId(null);
        atmRepository.save(atm);
    }
    @Override
    public List<Atm> findAll() {return atmRepository.findAll();}
    @Override
    public boolean existById(Long id) {return atmRepository.existsById(id);}
    @Override
    public Atm findById(Long id) {return atmRepository.getReferenceById(id);}

    @Transactional
    @Override
    public void update(Atm atm) throws JsonProcessingException {}

    @Transactional
    @Override
    public void update(Atm atm, Long id) throws JsonProcessingException {
        Atm atmBeforeUpdate = findById(id);
        atm.setCreatedBy(atmBeforeUpdate.getCreatedBy());
        atm.setCreatedAt(atmBeforeUpdate.getCreatedAt());
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(atmBeforeUpdate);
        atmRepository.save(atm);
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        atmRepository.deleteById(id);
    }
}
