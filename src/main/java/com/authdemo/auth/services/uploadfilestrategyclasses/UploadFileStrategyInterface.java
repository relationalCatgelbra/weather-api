package com.authdemo.auth.services.uploadfilestrategyclasses;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.authdemo.auth.models.WeatherResponseData;

public interface UploadFileStrategyInterface {

    public List<WeatherResponseData> execute(MultipartFile file) throws Exception;

}
