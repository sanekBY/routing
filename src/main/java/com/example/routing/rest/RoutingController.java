package com.example.routing.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.routing.model.CountryData;
import com.example.routing.service.RouteCalculator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RoutingController {

    private final RouteCalculator routeCalculator;

    @GetMapping("/routing/{origin}/{destination}")
    public List<String> getDistance(@PathVariable String origin,
                                    @PathVariable String destination) {
        if (StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination)) {
            return List.of();
        }
        var countryDataByCode = routeCalculator.calculateRoute(origin.toUpperCase());
        return Optional.ofNullable(countryDataByCode.get(destination.toUpperCase()))
            .map(CountryData::getShortestPath)
            .map(paths -> {
                var route = paths.stream().map(CountryData::getCca3).collect(Collectors.toList());
                route.add(destination);
                return route;
            })
            .orElse(List.of());

    }

}