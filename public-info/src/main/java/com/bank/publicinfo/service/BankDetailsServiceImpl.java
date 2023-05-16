package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.mappers.BankDetailsMapper;
import com.bank.publicinfo.model.BankDetails;
import com.bank.publicinfo.repositories.BankDetailsRepository;
import com.bank.publicinfo.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankDetailsServiceImpl implements BankDetailsService{
    private final BankDetailsRepository bankDetailsRepository;


    public BankDetailsServiceImpl(BankDetailsRepository bankDetailsRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
    }
    @Transactional
    @Override
    public void save(BankDetailsDto bankDetailsDto) {
        bankDetailsDto.setId(null);
        bankDetailsRepository.save(BankDetailsMapper.INSTANCE.toEntity(bankDetailsDto));
    }
    @Override
    public List<BankDetailsDto> findAll() {
        return bankDetailsRepository.findAll().stream()
            .map(BankDetailsMapper.INSTANCE::toDto)
            .toList();}
    @Override
    public boolean existById(Long id) {return bankDetailsRepository.existsById(id);}
    @Override
    public BankDetailsDto findById(Long id) {
        BankDetails bankDetails = bankDetailsRepository.getReferenceById(id);
        return BankDetailsMapper.INSTANCE.toDto(bankDetails);
    }
    @Transactional
    @Override
    public void update(BankDetailsDto bankDetailsDto, Long id) throws JsonProcessingException {
        BankDetailsDto bankDetailsBeforeUpdate = findById(id);
        BankDetails bankDetails = BankDetailsMapper.INSTANCE.toEntity(bankDetailsBeforeUpdate);
        if (bankDetails != null) {
            bankDetails.setCreatedBy("Somebody");
            bankDetails.setCreatedAt(LocalDateTime.now());
        }
        EntityJsonBeforeUpdateSaver.saveEntityJsonBeforeUpdate(bankDetails);
        bankDetailsRepository.save(bankDetails);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bankDetailsRepository.deleteById(id);
    }
}
