package com.authdemo.auth.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {

    private String parameter;

    private List<Coordinate> coordinates;

}
