package com.example.routing.converter;

import org.springframework.stereotype.Service;

import com.example.routing.dto.CountryDataDto;
import com.example.routing.model.Coordinate;
import com.example.routing.model.CountryData;

@Service
public class CountryDataDtoToModelConverter {

    public CountryData convert(CountryDataDto countryDataDto) {
        var countryData = new CountryData();
        countryData.setName(countryDataDto.getName().getCommon());
        countryData.setCca3(countryDataDto.getCca3());
        var coordinate = new Coordinate(countryDataDto.getLatlng().get(0), countryDataDto.getLatlng().get(1));
        countryData.setCoordinate(coordinate);
        return countryData;
    }
}