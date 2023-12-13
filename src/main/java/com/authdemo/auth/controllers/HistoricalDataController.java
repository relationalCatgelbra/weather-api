package com.authdemo.auth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.authdemo.auth.models.HistoricalDataRequestObject;
import com.authdemo.auth.models.WeatherResponseDataHistorical;
import com.authdemo.auth.services.HistoricalDataService;

@Controller
@RequestMapping("/api/v1/")
public class HistoricalDataController {

        private HistoricalDataService historicalDataService;

        public HistoricalDataController(HistoricalDataService historicalDataService) {
                this.historicalDataService = historicalDataService;

        }

        @PostMapping("/getdatabyconditions")
        public ResponseEntity<WeatherResponseDataHistorical> getAllDataByCityAndCountryAndTimeRange(
                        @RequestBody HistoricalDataRequestObject historicalDataRequestObject

        ) {
                return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(historicalDataService.prepareHistoricalObject(historicalDataRequestObject));

        }

}
