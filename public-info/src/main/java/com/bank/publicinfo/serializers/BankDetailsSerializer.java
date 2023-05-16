package com.bank.publicinfo.serializers;

import com.bank.publicinfo.model.BankDetails;
import com.bank.publicinfo.model.Certificate;
import com.bank.publicinfo.model.License;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankDetailsSerializer extends StdSerializer<BankDetails> {
    public BankDetailsSerializer() {
        this(null);
    }
    protected BankDetailsSerializer(Class<BankDetails> t) {
        super(t);
    }
    @Override
    public void serialize(BankDetails bankDetails, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<Long> certificateList = new ArrayList<>();
        List<Long> licenseList = new ArrayList<>();

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", String.valueOf(bankDetails.getId()));
        jsonGenerator.writeStringField("bik", String.valueOf(bankDetails.getBik()));
        jsonGenerator.writeStringField("inn", String.valueOf(bankDetails.getId()));
        jsonGenerator.writeStringField("kpp", String.valueOf(bankDetails.getKpp()));
        jsonGenerator.writeStringField("corAccount", String.valueOf(bankDetails.getCorAccount()));
        jsonGenerator.writeStringField("city", bankDetails.getCity());
        jsonGenerator.writeStringField("jointStockCompany", bankDetails.getJointStockCompany());
        jsonGenerator.writeStringField("name", bankDetails.getName());

        for (Certificate certificate : bankDetails.getCertificates()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", String.valueOf(certificate.getId()));
            jsonGenerator.writeStringField("photo", Arrays.toString(certificate.getPhoto()));
            jsonGenerator.writeStringField("bankDetailsId", String.valueOf(bankDetails.getId()));
            jsonGenerator.writeEndObject();
        }
        if (bankDetails.getCertificates() != null) {
            for (Certificate certificate : bankDetails.getCertificates())
                certificateList.add(certificate.getId());
        }
        if (bankDetails.getLicenses() != null) {
            for (License license : bankDetails.getLicenses())
                licenseList.add(license.getId());

            jsonGenerator.writeStringField("certificateList", String.valueOf(certificateList));
            jsonGenerator.writeStringField("licenseList", String.valueOf(licenseList));
            jsonGenerator.writeEndObject();


        }
    }
}
