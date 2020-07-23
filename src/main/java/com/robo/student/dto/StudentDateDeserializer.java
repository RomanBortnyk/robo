package com.robo.student.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentDateDeserializer extends JsonDeserializer<LocalDate> {

//    public StudentDateDeserializer(){
//        this(null);
//    }
//
//    public StudentDateDeserializer(Class<?> vc) {
//        super(vc);
//    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return LocalDate.parse(p.getText(), formatter);
    }
}
