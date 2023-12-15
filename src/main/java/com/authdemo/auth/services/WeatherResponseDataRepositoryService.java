package com.authdemo.auth.services;

import java.util.List;

import com.authdemo.auth.models.WeatherResponseData;
import com.authdemo.auth.repositories.WeatherResponseDataRepository;

public class WeatherResponseDataRepositoryService {

    private WeatherResponseDataRepository weatherResponseRepository;

    public WeatherResponseDataRepositoryService(WeatherResponseDataRepository weatherResponseDataRepository) {
        this.weatherResponseRepository = weatherResponseDataRepository;
    }

    public WeatherResponseData saveWeatherResponseData(WeatherResponseData weatherResponseData) {
        return weatherResponseRepository.save(weatherResponseData);
    }

    public List<WeatherResponseData> getAllWeatherDataByCountryAndCity(String countryName, String cityName) {
        return weatherResponseRepository.findByCountryAndCity(countryName, cityName);

    }

    public List<WeatherResponseData> getAllData() {
        return weatherResponseRepository.findAll();
    }

}
