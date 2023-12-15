package com.authdemo.auth.models.requestmodels;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
    @NotBlank
    @NotNull
    private String city;
    @NotBlank
    @NotNull
    private String country;
    @NotNull
    @NotBlank
    private String format;

}
