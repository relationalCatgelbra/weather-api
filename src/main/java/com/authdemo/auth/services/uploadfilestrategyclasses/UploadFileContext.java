package com.authdemo.auth.services.uploadfilestrategyclasses;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.authdemo.auth.models.WeatherResponseData;

@Component
public class UploadFileContext {

    private UploadFileStrategyInterface uploadFileStrategy;

    public void setStrategy(UploadFileStrategyInterface uploadFileStrategy) {
        this.uploadFileStrategy = uploadFileStrategy;

    }

    public List<WeatherResponseData> executeUploadFileStrategy(MultipartFile file) throws Exception {
        return uploadFileStrategy.execute(file);

    }
}
