package com.example.routing.service;

import com.example.routing.model.Coordinate;

public interface DistanceCalculator {

    double calculateDistanceBetweenCoordinates(Coordinate from, Coordinate to);

}