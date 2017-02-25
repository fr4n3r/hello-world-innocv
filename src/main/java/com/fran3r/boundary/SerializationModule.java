package com.fran3r.boundary;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;

import java.time.LocalDate;

/**
* @author Fran Alonso @ byteflair.com
*/
public class SerializationModule extends SimpleModule {
    public SerializationModule() {
        super("TimeModule", new Version(1, 0, 0, "SNAPSHOT", "com.fran3r", "fran3r-time"));
    }

    @Override
    public void setupModule(Module.SetupContext context) {
        super.setupModule(context);
        SimpleSerializers serializers = new SimpleSerializers();
        serializers.addSerializer(LocalDate.class, new LocalDateSerializer());
        context.addSerializers(serializers);

        SimpleDeserializers deserializers = new SimpleDeserializers();
        deserializers.addDeserializer(LocalDate.class, new LocalDateDeserializer());

        context.addDeserializers(deserializers);
    }
}
