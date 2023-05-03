package com.bank.publicinfo.serializers;

import com.bank.publicinfo.model.Certificate;
import com.bank.publicinfo.model.License;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Arrays;

public class LicenseSerializer extends StdSerializer<License> {
    public LicenseSerializer() {
        this(null);
    }

    protected LicenseSerializer(Class<License> t) {
        super(t);
    }

    @Override
    public void serialize(License license, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Long bankDetailsId;
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", String.valueOf(license.getId()));
        jsonGenerator.writeStringField("photo", Arrays.toString(license.getPhoto()));

        if (license.getBankDetails() != null) {
            bankDetailsId = license.getBankDetails().getId();
        }
        else
            bankDetailsId = null;
        jsonGenerator.writeStringField("branchId", String.valueOf(bankDetailsId));
        jsonGenerator.writeEndObject();
    }

}
