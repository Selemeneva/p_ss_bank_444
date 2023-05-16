package com.bank.publicinfo.model;

import com.bank.publicinfo.serializers.BankDetailsSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@JsonSerialize(using = BankDetailsSerializer.class)
@Entity
@Table(name="bank_details", schema = "public_bank_information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankDetails extends BaseClass{
    //реквизиты банка (ИНН, КПП, р/с и т.д.)
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 9)
    @Size (min = 9, max = 9, message = "bik must consist of 9 numbers")
    @Positive(message = "bik must be positive")
    private Long bik;
    @Column(unique = true)
  //  @Size (min = 10, max = 12, message = "inn must be from 10 to 12 numbers")
    @Positive(message = "inn must be positive")
    private Long inn;
    @Column(unique = true)
    @Size (min = 0, max = 9, message = "kpp must not more 9 symbols")
    @Positive(message = "kpp must be positive")
    private Long kpp;
    @Column(unique = true)
    @Size (min = 0, max = 20, message = "cor_account must be from 0 to 20 numbers")
    @Positive(message = "cor_account must be positive")
    private Integer corAccount;
    @Column
    @Size(max = 180, message = "length must be not more 180 symbols")
    private String city;
    @Column
    @Size(max = 15, message = "length must be not more 15 symbols")
    private String jointStockCompany;
    @Column
    @Size(max = 80, message = "length must be not more 80 symbols")
    private String name;
    @OneToMany(mappedBy = "bankDetails", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("bankDetails")
    private Set<Certificate> certificates= new HashSet<>();
    @OneToMany(mappedBy = "bankDetails", fetch = FetchType.LAZY)
    private Set<License> licenses = new HashSet<>();
}
