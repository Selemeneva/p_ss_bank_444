package com.bank.publicinfo.model;


import com.bank.publicinfo.serializers.CertificateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;

@JsonSerialize(using = CertificateSerializer.class)
@Entity
@Table(name = "certificate", schema = "public_bank_information")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Certificate extends BaseClass {
    //сертификаты банка
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private byte[] photo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankDetailsId", referencedColumnName = "id")
    private BankDetails bankDetails;



}
