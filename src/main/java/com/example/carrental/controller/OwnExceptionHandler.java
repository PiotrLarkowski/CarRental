package com.example.carrental.controller;

import com.example.carrental.domain.Car.CarRentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class OwnExceptionHandler {
//bledy domenowe, związane z funkcjonalnością
    @ExceptionHandler({CarRentException.class})
    public ResponseEntity<?> getResponseHttpNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({HttpClientErrorException.BadRequest.class})
    public ResponseEntity<?> getResponseHttpBadRequest() {
        return ResponseEntity.badRequest().build();
    }

}
