package com.example.routing.dto;

import java.util.List;

import lombok.Data;

@Data
public class CountryDataDto {

    private CountryNameDto name;
    private String cca3;
    private List<Long> latlng;
    private List<String> borders;
}