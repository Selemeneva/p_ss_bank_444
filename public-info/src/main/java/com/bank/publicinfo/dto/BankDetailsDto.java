package com.bank.publicinfo.dto;

import lombok.Data;
import java.util.Set;

@Data
public class BankDetailsDto {
    private Long id;
    private String bik;
    private Long inn;
    private Long kpp;
    private Integer corAccount;
    private String city;
    private String jointStockCompany;
    private String name;
    private Set<BranchDto> branchDtoSet;

}
