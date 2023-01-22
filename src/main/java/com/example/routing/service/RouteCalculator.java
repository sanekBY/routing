package com.example.routing.service;

import java.util.Map;

import com.example.routing.model.CountryData;

public interface RouteCalculator {

    Map<String, CountryData> calculateRoute(String fromCode);
}