package com.bank.publicinfo.dto;
import lombok.Data;
import java.sql.Time;

@Data
public class AtmDto {
    private Long id;
    private String address;
    private Time startOfWork;
    private Time endOfWork;
    private boolean allHours;
    private Long branchId;
}
