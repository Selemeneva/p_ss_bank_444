package com.bank.publicinfo.util;


import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.model.BankDetails;
import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.model.Certificate;
import com.bank.publicinfo.model.License;
import com.bank.publicinfo.service.AtmService;
import com.bank.publicinfo.service.AuditService;
import com.bank.publicinfo.service.BankDetailsService;
import com.bank.publicinfo.service.BranchService;
import com.bank.publicinfo.service.CertificateService;
import com.bank.publicinfo.service.LicenseService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.sql.Time;

@Component
public class Init {
    private final AtmService atmService;
    private final BankDetailsService bankDetailsService;
    private final BranchService branchService;
    private final CertificateService certificateService;
    private final LicenseService licenseService;
    private final AuditService auditService;


    public Init(AtmService atmService, BankDetailsService bankDetailsService, BranchService branchService,
                CertificateService certificateService, LicenseService licenseService, AuditService auditService) {
        this.atmService = atmService;
        this.bankDetailsService = bankDetailsService;
        this.branchService = branchService;
        this.certificateService = certificateService;
        this.licenseService = licenseService;
        this.auditService = auditService;
    }


    @PostConstruct
    public void initialization() throws IOException {
        BankDetails bankDetails = new BankDetails();
        bankDetails.setBik(123L);
        bankDetails.setInn(78116L);
        bankDetails.setKpp(7816011L);
        bankDetails.setCorAccount(30181L);
        bankDetails.setCity("Москва");
        bankDetails.setJointStockCompany("ЗАО АКБ");
        bankDetails.setName("Капиталл");
        bankDetailsService.save(bankDetails);

        Branch branch1 = new Branch();
        branch1.setAddress("Тверская ул., д. 28");
        branch1.setPhoneNumber(77777L);
        branch1.setCity("Москва");
        branch1.setStartOfWork(Time.valueOf("10:00:00"));
        branch1.setEndOfWork(Time.valueOf("19:00:00"));
        branchService.save(branch1);


  Atm atm1 = new Atm();
        atm1.setAddress("Тверская ул., д. 28");
        atm1.setStartOfWork(Time.valueOf("00:00:00"));
        atm1.setEndOfWork(Time.valueOf("00:00:00"));
        atm1.setAllHours(true);
        atm1.setBranch(branch1);
        atmService.save(atm1);



  Branch branch2 = new Branch();
        branch2.setAddress("Ямская ул., д. 82");
        branch2.setPhoneNumber(88888L);
        branch2.setCity("Москва");
        branch2.setStartOfWork(Time.valueOf("10:00:00"));
        branch2.setEndOfWork(Time.valueOf("19:00:00"));
        branchService.save(branch2);


      Atm atm2 = new Atm();
        atm2.setAddress("Ямская ул., д. 82");
        atm2.setStartOfWork(Time.valueOf("10:00:00"));
        atm2.setEndOfWork(Time.valueOf("19:00:00"));
        atm2.setAllHours(false);
        atm2.setBranch(branch2);
        atmService.save(atm2);


       /* Certificate certificate1 = new Certificate();
        byte[] bytes = ByteBuffer.allocate(4).putInt(8).array();

        certificate1.setPhoto(bytes);
        certificate1.setBankDetails(bankDetails);
        certificateService.save(certificate1);

        Certificate certificate2 = new Certificate();
        byte[] bytes1 = ByteBuffer.allocate(4).putInt(8).array();
        certificate2.setPhoto(bytes1);
        certificate2.setBankDetails(bankDetails);
        certificateService.save(certificate2);

        License license1 = new License();
        byte[] bytes2 = ByteBuffer.allocate(4).putInt(8).array();
        license1.setPhoto(bytes2);
        license1.setBankDetails(bankDetails);
        licenseService.save(license1);

        License license2 = new License();
        byte[] bytes3 = ByteBuffer.allocate(4).putInt(8).array();
        license2.setPhoto(bytes3);
        license2.setBankDetails(bankDetails);
        licenseService.save(license2);
*/
   }
}
