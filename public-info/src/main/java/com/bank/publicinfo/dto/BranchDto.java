package com.bank.publicinfo.dto;

import lombok.*;

import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchDto {
    private Long id;
    private String address;
    private Long phoneNumber;
    private String city;
    private LocalTime startOfWork;
    private LocalTime endOfWork;
    //private Long bankDetailsId;
}
