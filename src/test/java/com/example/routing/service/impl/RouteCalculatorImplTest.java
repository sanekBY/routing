package com.example.routing.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.routing.model.CountryData;
import com.example.routing.service.RouteCalculator;

@SpringBootTest
public class RouteCalculatorImplTest {

    @Autowired
    private RouteCalculator routeCalculator;

    @Test
    public void checkRouteFound() {
        var countryData = routeCalculator.calculateRoute("CZE");
        var routeToCountry = countryData.get("ITA").getShortestPath()
            .stream()
            .map(CountryData::getCca3)
            .collect(Collectors.toList());
        assertTrue(routeToCountry.contains("CZE"));
        assertTrue(routeToCountry.contains("AUT"));
    }

    @Test
    public void checkRouteNotFound() {
        var countryData = routeCalculator.calculateRoute("");
        assertTrue(countryData.isEmpty());
    }

}