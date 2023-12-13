package com.authdemo.auth.services;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.authdemo.auth.models.AuthResponseModel;

@Service
public class TokenClass {
    @Value("${meteomatics.api.username}")
    private String username;

    @Value("${meteomatics.api.password}")
    private String password;

    @Value("${meteomatics.api.url}")
    private String tokenUrl;

    public String getToken() {
        String authHeader = getAuthHeader(this.username, this.password);

        WebClient client = WebClient.builder()
                .baseUrl("https://login.meteomatics.com/api/v1/token")
                .build();

        AuthResponseModel responseModel = client.get()
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .bodyToMono(AuthResponseModel.class)
                .block();

        return responseModel.getAccessToken();

    }

    public String getAuthHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedAuth);
    }

}
