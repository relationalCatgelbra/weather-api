package com.authdemo.auth.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.authdemo.auth.models.WeatherResponseData;
import com.authdemo.auth.services.uploadfilestrategyclasses.UploadCsvFileStrategyImpl;
import com.authdemo.auth.services.uploadfilestrategyclasses.UploadFileContext;
import com.authdemo.auth.services.uploadfilestrategyclasses.UploadJsonFileStrategyImpl;
import com.authdemo.auth.services.uploadfilestrategyclasses.UploadXmlFileStrategyImpl;

@Service
public class FileUploadService {

    private WeatherResponseDataRepositoryService weatherResponseDataRepositoryService;

    private UploadFileContext uploadFileContext;

    public FileUploadService(WeatherResponseDataRepositoryService weatherResponseDataRepositoryService,
            UploadFileContext uploadFileContext) {
        this.weatherResponseDataRepositoryService = weatherResponseDataRepositoryService;
        this.uploadFileContext = uploadFileContext;

    }

    public List<WeatherResponseData> uploadFile(MultipartFile file) throws Exception {

        Optional<String> fileOriginalName = Optional.ofNullable(file.getOriginalFilename());

        if (fileOriginalName.isEmpty()) {
            throw new NullPointerException("there must be a file");
        }

        String fileName = fileOriginalName.get().toLowerCase();

        if (fileName.endsWith(".json")) {
            uploadFileContext.setStrategy(new UploadJsonFileStrategyImpl());
        } else if (fileName.endsWith(".csv")) {
            uploadFileContext.setStrategy(new UploadCsvFileStrategyImpl());
        } else if (fileName.endsWith(".xml")) {
            uploadFileContext.setStrategy(new UploadXmlFileStrategyImpl());
        }

        List<WeatherResponseData> result = uploadFileContext.executeUploadFileStrategy(file);

        result
                .stream()
                .forEach(
                        weatherResponseData -> weatherResponseDataRepositoryService
                                .saveWeatherResponseData(weatherResponseData));

        return result;

    }

}
