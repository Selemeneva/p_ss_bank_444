package com.bank.publicinfo.dto;

import com.bank.publicinfo.model.BankDetails;
import lombok.Data;

import java.sql.Time;

@Data
public class BranchDto {
    private Long id;
    private String address;
    private Long phoneNumber;
    private String city;
    private Time startOfWork;
    private Time endOfWork;
    private Long bankDetailsId;
}
