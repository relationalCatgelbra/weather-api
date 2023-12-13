package com.authdemo.auth.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xml.sax.SAXException;

import com.opencsv.exceptions.CsvValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<Object> dataNotFound(IOException e) {

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", HttpStatus.BAD_REQUEST.value());
        errorBody.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorBody.put("message", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorBody);

    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> noResourceAvailable(NullPointerException e) {

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorBody.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorBody.put("message", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorBody);

    }

    @ExceptionHandler(value = CsvValidationException.class)
    public ResponseEntity<Object> csvValidationError(CsvValidationException e) {

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", HttpStatus.BAD_REQUEST.value());
        errorBody.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorBody.put("message", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorBody);

    }

    @ExceptionHandler(value = ParserConfigurationException.class)
    public ResponseEntity<Object> parserConfigException(ParserConfigurationException e) {

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", HttpStatus.BAD_REQUEST.value());
        errorBody.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorBody.put("message", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorBody);

    }

    @ExceptionHandler(value = SAXException.class)
    public ResponseEntity<Object> getSaxException(SAXException e) {

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", HttpStatus.BAD_REQUEST.value());
        errorBody.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorBody.put("message", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorBody);

    }

}
