package com.example.routing.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.routing.dto.CountryDataDto;
import com.example.routing.service.DataExtractor;

@SpringBootTest
public class JsonDataExtractorTest {

    @Autowired
    private DataExtractor dataExtractor;

    @Test
    public void testDataExtractor() {
        List<CountryDataDto> countryDataDtos = dataExtractor.extractData();
        assertFalse(countryDataDtos.isEmpty());
    }

}