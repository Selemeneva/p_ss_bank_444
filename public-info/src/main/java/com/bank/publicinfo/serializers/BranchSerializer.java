package com.bank.publicinfo.serializers;

import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.model.Branch;
import com.bank.publicinfo.model.License;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BranchSerializer extends StdSerializer<Branch> {
    public BranchSerializer() {
        this(null);
    }

    protected BranchSerializer(Class<Branch> type) {
        super(type);
    }

    @Override
    public void serialize(Branch branch, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        List<Long> atmList = new ArrayList<>();

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", String.valueOf(branch.getId()));
        jsonGenerator.writeStringField("address", branch.getAddress());
        jsonGenerator.writeStringField("phoneNumber", String.valueOf(branch.getPhoneNumber()));
        jsonGenerator.writeStringField("city", branch.getCity());
        jsonGenerator.writeStringField("startOfWork", String.valueOf(branch.getStartOfWork()));
        jsonGenerator.writeStringField("endOfWork", String.valueOf(branch.getEndOfWork()));

        for (Atm atm : branch.getAtms()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", String.valueOf(atm.getId()));
            jsonGenerator.writeStringField("address", atm.getAddress());
            jsonGenerator.writeStringField("startOfWork", String.valueOf(atm.getStartOfWork()));
            jsonGenerator.writeStringField("endOfWork", String.valueOf(atm.getEndOfWork()));
            jsonGenerator.writeBooleanField("allHours", atm.isAllHours());
            jsonGenerator.writeStringField("branchId", String.valueOf(branch.getId()));

        }
        if (branch.getAtms() != null) {
            for (Atm atm : branch.getAtms())
                atmList.add(atm.getId());
            jsonGenerator.writeStringField("atmList", String.valueOf(atmList));
            jsonGenerator.writeEndObject();


        }
    }
}
