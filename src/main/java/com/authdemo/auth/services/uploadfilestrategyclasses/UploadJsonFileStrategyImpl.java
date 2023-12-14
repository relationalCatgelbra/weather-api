package com.authdemo.auth.services.uploadfilestrategyclasses;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.authdemo.auth.models.WeatherResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UploadJsonFileStrategyImpl implements UploadFileStrategyInterface {

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<WeatherResponseData> execute(MultipartFile file) throws IOException {

        return objectMapper
                .readValue(file.getInputStream(),
                        objectMapper.getTypeFactory()
                                .constructCollectionType(
                                        List.class,
                                        WeatherResponseData.class));

    }

}
