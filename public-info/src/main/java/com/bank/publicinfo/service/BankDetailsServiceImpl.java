package com.bank.publicinfo.service;

import com.bank.publicinfo.model.BankDetails;
import com.bank.publicinfo.repositories.BankDetailsRepository;
import com.bank.publicinfo.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankDetailsServiceImpl implements BankDetailsService{
    private final BankDetailsRepository bankDetailsRepository;


    public BankDetailsServiceImpl(BankDetailsRepository bankDetailsRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
    }
    @Transactional
    @Override
    public void save(BankDetails bankDetails) {
        bankDetails.setId(null);
        bankDetailsRepository.save(bankDetails);
    }
    @Override
    public List<BankDetails> findAll() {return bankDetailsRepository.findAll();}
    @Override
    public boolean existById(Long id) {return bankDetailsRepository.existsById(id);}
    @Override
    public BankDetails findById(Long id) {return bankDetailsRepository.getReferenceById(id);}
    @Transactional
    @Override
    public void update(BankDetails bankDetails) throws JsonProcessingException {}

    @Transactional
    @Override
    public void update(BankDetails bankDetails, Long id) throws JsonProcessingException {
        BankDetails bankDetailsBeforeUpdate = findById(id);
        bankDetails.setCreatedBy(bankDetailsBeforeUpdate.getCreatedBy());
        bankDetails.setCreatedAt(bankDetailsBeforeUpdate.getCreatedAt());
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(bankDetailsBeforeUpdate);
        bankDetailsRepository.save(bankDetails);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bankDetailsRepository.deleteById(id);
    }
}
