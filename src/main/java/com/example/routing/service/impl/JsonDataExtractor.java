package com.example.routing.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.routing.dto.CountryDataDto;
import com.example.routing.service.DataExtractor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class JsonDataExtractor implements DataExtractor {

    private static final String FILE_NAME = "data.json";

    @Override
    @SneakyThrows
    public List<CountryDataDto> extractData() {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper.readValue(getFileFromResourceAsStream(FILE_NAME),
            new TypeReference<>() {});
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

}