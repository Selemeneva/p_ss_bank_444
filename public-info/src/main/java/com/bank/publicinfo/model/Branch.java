package com.bank.publicinfo.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="branch", schema = "public_bank_information")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    private Time startOfWork;
    @Column
    @Size (min = 5, max = 5, message = "time must be in XX:XX format")
    private Time endOfWork;
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("branch")
    private Set<Atm> atms = new HashSet<>();
}
