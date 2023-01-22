package com.example.routing.service;

import java.util.Map;

import com.example.routing.model.CountryData;

public interface CountryDataHolder {

    Map<String, CountryData> getCountriesByCode();
}
