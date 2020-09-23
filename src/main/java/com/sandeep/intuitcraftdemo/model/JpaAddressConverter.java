package com.sandeep.intuitcraftdemo.model;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JpaAddressConverter implements AttributeConverter<Address, String> {

    // ObjectMapper is thread safe
    private final static ObjectMapper objectMapper = new ObjectMapper();

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String convertToDatabaseColumn(Address attribute) {
        String jsonString = "";
        try {
            log.debug("Start convertToDatabaseColumn");

            // convert list of POJO to json
            jsonString = objectMapper.writeValueAsString(attribute);
            log.debug("convertToDatabaseColumn" + jsonString);

        } catch (JsonProcessingException ex) {
            log.error(ex.getMessage());
        }
        return jsonString;
    }

    @Override
    public Address convertToEntityAttribute(String dbData) {

    	Address Address = new Address();
        try {
            log.debug("Start convertToEntityAttribute");

            // convert json to list of POJO
            Address = objectMapper.readValue(dbData, Address.class);
            log.debug("JsonDocumentsConverter.convertToDatabaseColumn" + Address);

        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
		return Address;

    }
}