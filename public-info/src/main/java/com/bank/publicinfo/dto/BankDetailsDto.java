package com.bank.publicinfo.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankDetailsDto {
    private Long id;
    private Long bik;
    private Long inn;
    private Long kpp;
    private Integer corAccount;
    private String city;
    private String jointStockCompany;
    private String name;
   // private Set<BranchDto> branchDtoSet;

}
