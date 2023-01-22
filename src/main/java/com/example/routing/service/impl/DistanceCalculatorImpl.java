package com.example.routing.service.impl;

import org.springframework.stereotype.Service;

import com.example.routing.model.Coordinate;
import com.example.routing.service.DistanceCalculator;

@Service
public class DistanceCalculatorImpl implements DistanceCalculator {

    @Override
    public double calculateDistanceBetweenCoordinates(Coordinate from, Coordinate to) {

        var lat1 = Math.toRadians(from.getLat());
        var lon1 = Math.toRadians(from.getLng());
        var lat2 = Math.toRadians(to.getLat());
        var lon2 = Math.toRadians(to.getLng());

        double earthRadius = 6371.01; //Kilometers
        return earthRadius * Math.acos(
            Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }
}