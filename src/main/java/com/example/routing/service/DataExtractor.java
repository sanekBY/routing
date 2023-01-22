package com.example.routing.service;

import java.util.List;

import com.example.routing.dto.CountryDataDto;

public interface DataExtractor {

    List<CountryDataDto> extractData();
}
