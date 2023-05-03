package com.bank.publicinfo.serializers;

import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.model.Certificate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Arrays;

public class CertificateSerializer extends StdSerializer<Certificate> {
    public CertificateSerializer() {
        this(null);
    }

    protected CertificateSerializer(Class<Certificate> t) {
        super(t);
    }

    @Override
    public void serialize(Certificate certificate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Long bankDetailsId;
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", String.valueOf(certificate.getId()));
        jsonGenerator.writeStringField("photo", Arrays.toString(certificate.getPhoto()));

        if (certificate.getBankDetails() != null) {
            bankDetailsId = certificate.getBankDetails().getId();
        }
        else
            bankDetailsId = null;
        jsonGenerator.writeStringField("branchId", String.valueOf(bankDetailsId));
        jsonGenerator.writeEndObject();
    }

}
