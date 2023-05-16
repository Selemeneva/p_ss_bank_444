package com.bank.publicinfo.util;


import com.bank.publicinfo.dto.*;
import com.bank.publicinfo.mappers.*;
import com.bank.publicinfo.model.*;
import com.bank.publicinfo.service.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        bankDetails.setCorAccount(30181);
        bankDetails.setCity("Москва");
        bankDetails.setJointStockCompany("ЗАО АКБ");
        bankDetails.setName("Капиталл");
        BankDetailsDto bankDetailsDto = BankDetailsMapper.INSTANCE.toDto(bankDetails);
        bankDetailsService.save(bankDetailsDto);

        Branch branch1 = new Branch();
        branch1.setAddress("Тверская ул., д. 28");
        branch1.setPhoneNumber(77777L);
        branch1.setCity("Москва");
        branch1.setStartOfWork(LocalTime.parse("10:00:00"));
        branch1.setEndOfWork(LocalTime.parse("19:00:00"));
        BranchDto branchDto = BranchMapper.INSTANCE.toDto(branch1);
        branchService.save(branchDto);

        Branch branch2 = new Branch();
        branch2.setAddress("Ямская ул., д. 82");
        branch2.setPhoneNumber(88888L);
        branch2.setCity("Москва");
        branch2.setStartOfWork(LocalTime.parse("10:00:00"));
        branch2.setEndOfWork(LocalTime.parse("19:00:00"));
        BranchDto branchDto1 = BranchMapper.INSTANCE.toDto(branch2);
        branchService.save(branchDto1);

        Atm atm1 = new Atm();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        atm1.setAddress("Тверская ул., д. 28");
        atm1.setStartOfWork(LocalTime.parse("00:00:00", formatter));
        atm1.setEndOfWork(LocalTime.parse("00:00:00", formatter));
        atm1.setAllHours(true);
        atm1.setBranch(branch1);
        AtmDto atm1Dto = AtmMapper.INSTANCE.toDto(atm1);
        atm1Dto.setBranchId(1L);
        atmService.save(atm1Dto);


        Atm atm2 = new Atm();
        atm2.setAddress("Ямская ул., д. 82");
        atm2.setStartOfWork(LocalTime.parse("10:00:00"));
        atm2.setEndOfWork(LocalTime.parse("19:00:00"));
        atm2.setAllHours(false);
        atm2.setBranch(branch2);
        AtmDto atm2Dto = AtmMapper.INSTANCE.toDto(atm2);
        atm2Dto.setBranchId(2L);
        atmService.save(atm2Dto);

        Certificate certificate1 = new Certificate();
        byte[] bytes1 = ByteBuffer.allocate(4).putInt(8).array();

        Certificate certificate2 = new Certificate();
        byte[] bytes2 = ByteBuffer.allocate(4).putInt(8).array();

        certificate1.setPhoto(bytes1);
        certificate1.setBankDetails(bankDetails);
        certificate2.setPhoto(bytes2);
        certificate2.setBankDetails(bankDetails);

        CertificateDto certificateDto1 = CertificateMapper.INSTANCE.toDto(certificate1);
        CertificateDto certificateDto2 = CertificateMapper.INSTANCE.toDto(certificate2);
        certificateDto1.setBankDetailsId(1L);
        certificateDto2.setBankDetailsId(1L);
        certificateService.save(certificateDto1);
        certificateService.save(certificateDto2);

        License license1 = new License();
        byte[] bytes3 = ByteBuffer.allocate(4).putInt(8).array();

        License license2 = new License();
        byte[] bytes4 = ByteBuffer.allocate(4).putInt(8).array();

        license1.setPhoto(bytes3);
        license1.setBankDetails(bankDetails);
        license2.setPhoto(bytes4);
        license2.setBankDetails(bankDetails);

        LicenseDto licenseDto1 = LicenseMapper.INSTANCE.toDto(license1);
        LicenseDto licenseDto2 = LicenseMapper.INSTANCE.toDto(license2);
        licenseDto1.setBankDetailsId(1L);
        licenseDto2.setBankDetailsId(1L);
        licenseService.save(licenseDto1);
        licenseService.save(licenseDto2);
   }
}
