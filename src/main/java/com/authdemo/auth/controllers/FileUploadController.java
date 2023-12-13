package com.authdemo.auth.controllers;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.authdemo.auth.services.FileUploadService;
import com.opencsv.exceptions.CsvValidationException;

//multipart/form-data; boundary=<calculated when request is sent>

@Controller
@RequestMapping("/uploadfile")
public class FileUploadController {

    private FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;

    }

    @PostMapping("/savefile")
    public ResponseEntity<?> uploadWeatherFile(
            @RequestParam("file") MultipartFile file) throws NullPointerException {

        try {

            if (file == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("File is null");
            }

            String originalFileName = file.getOriginalFilename();

            if (originalFileName == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("File is null");
            }

            String fileName = originalFileName.toLowerCase();

            if (fileName.endsWith(".json")) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(fileUploadService.uploadJsonFile(file));
            } else if (fileName.endsWith(".csv")) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(fileUploadService.uploadCSVFile(file));
            } else if (fileName.endsWith(".xml")) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(fileUploadService.uploadXmlFile(file));

            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Not Found");
            }

        } catch (IOException | CsvValidationException
                | NullPointerException | ParserConfigurationException | SAXException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
