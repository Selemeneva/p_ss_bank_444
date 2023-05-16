package com.bank.publicinfo.model;


import com.bank.publicinfo.serializers.BranchSerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;


@JsonSerialize(using = BranchSerializer.class)
@Entity
@Table(name="branch", schema = "public_bank_information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Builder
public class Branch extends BaseClass{
    //отделение банка - адрес, телефон, часы работы, банкоматы

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Size(min = 1, max = 370, message = "address must be from 1 to 370 symbols")
    private String address;
    @Column(unique = true)
    @Size (min = 0, message = "phone number can't be negative")
    private Long phoneNumber;
    @Column
    @Size (min = 0, max = 250, message = "city must be not more then 250 symbols")
    private String city;
    @Column
    @Size (min = 5, max = 5, message = "time must be in XX:XX format")
    private LocalTime startOfWork;
    @Column
    @Size (min = 5, max = 5, message = "time must be in XX:XX format")
    private LocalTime endOfWork;
    @OneToMany(mappedBy = "branch", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("branch")
    private Set<Atm> atms = new HashSet<>();
}
