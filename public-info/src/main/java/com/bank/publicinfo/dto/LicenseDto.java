package com.bank.publicinfo.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LicenseDto {
    private Long id;
    private byte[] photo;
    private Long bankDetailsId;
}
