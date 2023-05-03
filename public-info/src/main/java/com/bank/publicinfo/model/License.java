package com.bank.publicinfo.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="license", schema = "public_bank_information")
@Data
@NoArgsConstructor
public class License extends BaseClass{
    //лицензии банка
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_details_id")
    private BankDetails bankDetails;


}
