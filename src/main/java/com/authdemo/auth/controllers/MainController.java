package com.authdemo.auth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.authdemo.auth.models.WeatherResponseData;
import com.authdemo.auth.models.requestmodels.LocationRequest;
import com.authdemo.auth.services.WeatherResponseDataService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1")
public class MainController {

        private WeatherResponseDataService weatherResponseDataService;

        public MainController(WeatherResponseDataService weatherResponseDataService) {
                this.weatherResponseDataService = weatherResponseDataService;
        }

        @PostMapping("/currentdata")
        public ResponseEntity<WeatherResponseData> getLocation(
                        @Valid @RequestBody LocationRequest locationRequest) {

                return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(weatherResponseDataService.getCurrentWeatherData(locationRequest));

        }

}
