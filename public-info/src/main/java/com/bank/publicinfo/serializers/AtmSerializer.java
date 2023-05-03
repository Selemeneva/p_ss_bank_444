package com.bank.publicinfo.serializers;

import com.bank.publicinfo.model.Atm;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AtmSerializer extends StdSerializer<Atm> {
    public AtmSerializer() {
        this(null);
    }

    protected AtmSerializer(Class<Atm> t) {
        super(t);
    }

    @Override
    public void serialize(Atm atm, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        Long branchId;
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", String.valueOf(atm.getId()));
        jsonGenerator.writeStringField("address", atm.getAddress());
        jsonGenerator.writeStringField("startOfWork", String.valueOf(atm.getStartOfWork()));
        jsonGenerator.writeStringField("endOfWork", String.valueOf(atm.getEndOfWork()));
        jsonGenerator.writeStringField("allHours", String.valueOf(atm.isAllHours()));
        jsonGenerator.writeStringField("branchId", String.valueOf(atm.getBranch().getId()));
        if (atm.getBranch() != null)
            branchId = atm.getBranch().getId();
        else
            branchId = null;
        jsonGenerator.writeStringField("branchId", String.valueOf(branchId));
        jsonGenerator.writeEndObject();

    }
}
