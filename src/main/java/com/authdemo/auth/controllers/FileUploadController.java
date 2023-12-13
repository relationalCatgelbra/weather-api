package com.authdemo.auth.controllers;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.authdemo.auth.models.WeatherResponseData;
import com.authdemo.auth.services.FileUploadService;
import com.opencsv.exceptions.CsvValidationException;

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
            @RequestParam("file") MultipartFile file) throws IOException, ParserConfigurationException,
            SAXException {
        if (file == null) {
            throw new NullPointerException("you must provide an xml file");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileUploadService.uploadXmlFile(file));

    }

    @PostMapping("/json")
    public ResponseEntity<List<WeatherResponseData>> uploadJson(
            @RequestParam("file") MultipartFile file) throws IOException {
        if (file == null) {
            throw new NullPointerException("you must provide a json file");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileUploadService.uploadJsonFile(file));

    }

    @PostMapping("/csv")
    public ResponseEntity<List<WeatherResponseData>> uploadCsv(
            @RequestParam("file") MultipartFile file) throws IOException, CsvValidationException {
        if (file == null) {
            throw new NullPointerException("you must provide a csv file");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileUploadService.uploadCsvFile(file));

    }

}
