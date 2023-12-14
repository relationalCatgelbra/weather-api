package com.authdemo.auth.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.authdemo.auth.models.WeatherResponseData;
import com.authdemo.auth.services.FileUploadService;

//multipart/form-data; boundary=<calculated when request is sent>

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;

    }

    @PostMapping("/xml")
    public ResponseEntity<List<WeatherResponseData>> uploadXml(
            @RequestParam("file") MultipartFile file) throws Exception {
        if (file == null) {
            throw new NullPointerException("you must provide a file");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileUploadService.uploadFile(file));
    }

}
