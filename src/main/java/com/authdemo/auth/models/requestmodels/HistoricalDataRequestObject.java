package com.authdemo.auth.models.requestmodels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalDataRequestObject {

    @NotNull
    @NotBlank
    private String country;
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String startDate;

    @NotNull
    @NotBlank
    private String endDate;
    @NotNull
    @NotBlank
    private String startTime;
    @NotNull
    @NotBlank
    private String endTime;

}
