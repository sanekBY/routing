package com.example.routing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.routing.converter.CountryDataDtoToModelConverter;
import com.example.routing.dto.CountryDataDto;
import com.example.routing.model.CountryData;
import com.example.routing.service.CountryDataHolder;
import com.example.routing.service.DataExtractor;
import com.example.routing.service.DistanceCalculator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryDataHolderImpl implements CountryDataHolder {
    private static final Map<String, CountryData> COUNTRY_DATA_MAP = new HashMap<>();

    private final DataExtractor dataExtractor;
    private final CountryDataDtoToModelConverter converter;
    private final DistanceCalculator distanceCalculator;

    @PostConstruct
    public void init() {
        var dataDtos = dataExtractor.extractData();
        COUNTRY_DATA_MAP.putAll(dataDtos.stream()
            .map(converter::convert)
            .collect(Collectors.toMap(CountryData::getCca3, Function.identity())));
        initCountryNeighbors(dataDtos);
    }

    private void initCountryNeighbors(List<CountryDataDto> dataDtos) {
        dataDtos.forEach(countryDataDto -> {
            var countryData = COUNTRY_DATA_MAP.get(countryDataDto.getCca3());
            countryDataDto.getBorders().forEach(borderCode -> {
                var borderedCountry = COUNTRY_DATA_MAP.get(borderCode);
                double distance = distanceCalculator.calculateDistanceBetweenCoordinates(countryData.getCoordinate(),
                    borderedCountry.getCoordinate());
                countryData.getBorderedCountries().put(borderedCountry, distance);
            });
        });

    }

    @Override
    public Map<String, CountryData> getCountriesByCode() {
        return COUNTRY_DATA_MAP;
    }
}