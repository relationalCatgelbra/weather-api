package com.authdemo.auth.exception;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.xml.sax.SAXException;

import com.opencsv.exceptions.CsvValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<String> dataNotFound(IOException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());

    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<String> noResourceAvailable(NullPointerException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());

    }

    @ExceptionHandler(value = CsvValidationException.class)
    public ResponseEntity<String> csvValidationError(CsvValidationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(value = ParserConfigurationException.class)
    public ResponseEntity<String> parserConfigException(ParserConfigurationException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(e.getMessage());

    }

    @ExceptionHandler(value = SAXException.class)
    public ResponseEntity<String> getSaxException(SAXException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());

    }

}
