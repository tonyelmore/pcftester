package com.telmore.domain;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class ItemSerializer extends JsonSerializer<Item> {
 
    @Override
    public void serialize(Item item, 
                          JsonGenerator jsonGenerator, 
                          SerializerProvider serializerProvider) 
          throws  IOException, 
                  JsonProcessingException {
  
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", item.getId());
        jsonGenerator.writeStringField("name", item.getName());
        jsonGenerator.writeNumberField("age", item.getAge());
        jsonGenerator.writeEndObject();
    }
 
}   