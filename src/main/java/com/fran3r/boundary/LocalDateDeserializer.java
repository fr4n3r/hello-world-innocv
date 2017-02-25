package com.fran3r.boundary;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Fran Alonso @ byteflair.com
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        ObjectCodec objectCodec = parser.getCodec();
        TextNode textNode = objectCodec.readTree(parser);
        String dateString = textNode.textValue();

        return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
    }
}
