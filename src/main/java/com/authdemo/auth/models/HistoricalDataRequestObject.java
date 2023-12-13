package com.authdemo.auth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalDataRequestObject {

    private String country;

    private String city;

    private String startDate;

    private String endDate;

    private String startTime;

    private String endTime;

}
