package com.example.routing.service.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.routing.model.CountryData;
import com.example.routing.service.CountryDataHolder;
import com.example.routing.service.RouteCalculator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouteCalculatorImpl implements RouteCalculator {

    private final CountryDataHolder countryDataHolder;
    private final Set<CountryData> settledCountries = new HashSet<>();
    private final Set<CountryData> unSettledCountries = new HashSet<>();

    @Override
    @Cacheable("routes")
    public Map<String, CountryData> calculateRoute(String fromCode) {
        var countriesByCode = countryDataHolder.getCountriesByCode();

        var from = countriesByCode.get(fromCode);
        if (Objects.isNull(from)) {
            return Map.of();
        }
        from.setDistance(0d);
        unSettledCountries.add(from);

        while (!unSettledCountries.isEmpty()) {
            var next = unSettledCountries.iterator().next();// TODO get lowest
            unSettledCountries.remove(next);

            for (Entry<CountryData, Double> borderCountryEntry : next.getBorderedCountries().entrySet()) {
                CountryData borderCountry = borderCountryEntry.getKey();
                if (!settledCountries.contains(borderCountry)) {
                    Double distance = borderCountryEntry.getValue();
                    double fullRouteDistance = distance + next.getDistance();
                    if (fullRouteDistance < borderCountry.getDistance()) {
                        borderCountry.setDistance(fullRouteDistance);
                        var shortestPath = new LinkedList<>(next.getShortestPath());
                        shortestPath.add(next);
                        borderCountry.setShortestPath(shortestPath);
                    }
                    unSettledCountries.add(borderCountry);
                }
            }
            settledCountries.add(next);
        }

        return countriesByCode;
    }
}