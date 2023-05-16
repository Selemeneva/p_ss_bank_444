package com.bank.publicinfo.dto;
import lombok.*;

import java.sql.Time;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtmDto {
    private Long id;
    private String address;
    private LocalTime startOfWork;
    private LocalTime endOfWork;
    private boolean allHours;
    private Long branchId;
}
