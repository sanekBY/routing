package com.example.routing.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.EqualsAndHashCode.Include;
import lombok.ToString;

@Data
@EqualsAndHashCode
public class CountryData {
    @Exclude
    private String name;
    @Include
    private String cca3;
    @Exclude
    private Coordinate coordinate;
    @Exclude
    private Double distance = Double.MAX_VALUE;
    @Exclude
    @ToString.Exclude
    private List<CountryData> shortestPath = new LinkedList<>();
    @Exclude
    @ToString.Exclude
    private Map<CountryData, Double> borderedCountries = new HashMap<>();
}