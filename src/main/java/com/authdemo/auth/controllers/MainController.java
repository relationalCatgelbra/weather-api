package com.authdemo.auth.controllers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.authdemo.auth.models.Location;
import com.authdemo.auth.models.LocationRequest;
import com.authdemo.auth.models.WeatherApiResponse;
import com.authdemo.auth.models.WeatherData;
import com.authdemo.auth.models.WeatherResponseData;
import com.authdemo.auth.services.CoordinatesService;
import com.authdemo.auth.services.TokenClass;
import com.authdemo.auth.services.WeatherResponseDataService;

@Controller
@RequestMapping("/api/v1")
public class MainController {

        private TokenClass tokenClass;

        private CoordinatesService coordinatesService;

        private WeatherResponseDataService weatherResponseDataService;

        private final Logger logger = LoggerFactory.getLogger(MainController.class);

        public MainController(TokenClass tokenClass,
                        CoordinatesService coordinatesService,
                        WeatherResponseDataService weatherResponseDataService) {
                this.tokenClass = tokenClass;
                this.coordinatesService = coordinatesService;
                this.weatherResponseDataService = weatherResponseDataService;
        }

        @PostMapping("/currentdata")
        public ResponseEntity<?> getLocation(
                        @RequestBody LocationRequest locationRequest) {

                String weatherApiBaseUrl = weatherResponseDataService.getWeatherApiBaseUrl();

                String tokenString = tokenClass.getToken();

                Location location = coordinatesService
                                .getCoordinatesByCityAndCountry(locationRequest.getCity(),
                                                locationRequest.getCountry(), locationRequest.getFormat());

                String coordinates = String.join(",", location.getLon(), location.getLat());

                String localDate = getLocalDate();

                String uriString = UriComponentsBuilder.fromUriString(weatherApiBaseUrl)
                                .pathSegment(localDate)
                                .pathSegment(weatherResponseDataService.getWeatherApiParameters())
                                .pathSegment(coordinates)
                                .pathSegment("json")
                                .build()
                                .toUriString();

                this.logger.info(uriString);

                List<WeatherData> weatherResponse = WebClient.create(uriString)
                                .get()
                                .uri("?access_token={token}", tokenString)
                                .retrieve()
                                .bodyToMono(WeatherApiResponse.class)
                                .block()
                                .getData();

                WeatherResponseData weatherResponseData = weatherResponseDataService.toWeatherResponseDataObject(
                                weatherResponse, locationRequest.getCountry(), locationRequest.getCity());

                WeatherResponseData weatherAnswer = weatherResponseDataService
                                .saveWeatherResponseData(weatherResponseData);

                return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(weatherAnswer);

        }

        private String getLocalDate() {
                ZoneOffset zoneOffset = ZoneOffset.ofHours(-5);
                LocalDateTime localDateTime = LocalDateTime.now().withNano(0);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

                return localDateTime.atOffset(zoneOffset).format(formatter);

        }

}
