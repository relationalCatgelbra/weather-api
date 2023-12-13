package com.authdemo.auth.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.authdemo.auth.models.HistoricalDataRequestObject;
import com.authdemo.auth.models.WeatherResponseData;
import com.authdemo.auth.models.WeatherResponseDataHistorical;
import com.authdemo.auth.models.WeatherResponseDataOperations;
import com.authdemo.auth.services.WeatherResponseDataOperationsService;
import com.authdemo.auth.services.WeatherResponseDataService;

@Controller
@RequestMapping("/api/v1/")
public class HistoricalDataController {

        private WeatherResponseDataService weatherResponseDataService;

        private WeatherResponseDataOperationsService weatherResponseDataOperationsService;

        public HistoricalDataController(WeatherResponseDataService weatherResponseDataService,
                        WeatherResponseDataOperationsService weatherResponseDataOperationsService) {
                this.weatherResponseDataService = weatherResponseDataService;
                this.weatherResponseDataOperationsService = weatherResponseDataOperationsService;

        }

        @PostMapping("/getallweatherdata")
        public ResponseEntity<WeatherResponseDataHistorical> getAllDataByCityAndCountryAndTimeRange(
                        @RequestBody HistoricalDataRequestObject historicalDataRequestObject

        ) {

                String startDate = historicalDataRequestObject.getStartDate();

                String endDate = historicalDataRequestObject.getEndDate();

                String startTime = historicalDataRequestObject.getStartTime();

                String endTime = historicalDataRequestObject.getEndTime();

                String formatter = "yyyy-MM-dd'T'HH:mm:ss";

                LocalDateTime startLocalDateTime = weatherResponseDataService
                                .fuseDateAndTime(startDate, startTime, formatter);

                LocalDateTime endLocalDateTime = weatherResponseDataService
                                .fuseDateAndTime(endDate, endTime, formatter);

                List<WeatherResponseData> weatherResponseDataByCityCountry = weatherResponseDataService
                                .getAllWeatherDataByCountryAndCity(
                                                historicalDataRequestObject.getCountry(),
                                                historicalDataRequestObject.getCity());

                List<WeatherResponseData> weatherResponseDataFiltered = weatherResponseDataService
                                .filterDateTimeInRange(weatherResponseDataByCityCountry, startLocalDateTime,
                                                endLocalDateTime);

                List<WeatherResponseDataOperations> weatherResponseDataOperations = List
                                .of(weatherResponseDataOperationsService
                                                .getMeanOfWeatherResponseData(weatherResponseDataFiltered));

                WeatherResponseDataHistorical weatherResponseDataHistorical = new WeatherResponseDataHistorical();

                weatherResponseDataHistorical.setWeatherResponseDataList(weatherResponseDataFiltered);
                weatherResponseDataHistorical.setWeatherResponseDataOperations(weatherResponseDataOperations);

                return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(weatherResponseDataHistorical);

        }

}
