package com.example.carrental.controller;

import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.RentalOffice.CarRentalOfficeException;
import com.example.carrental.domain.User.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class OwnExceptionHandler {
//bledy domenowe, związane z funkcjonalnością
    @ExceptionHandler({CarException.class})
    public ResponseEntity<?> getResponseHttpNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({HttpClientErrorException.BadRequest.class})
    public ResponseEntity<?> getResponseHttpBadRequest() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({UserException.class})
    public ResponseEntity<?> getResponseHttpClientNotFound(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler({CarRentalOfficeException.class})
    public ResponseEntity<?> getResponseHttpRentalNotFound(){ return ResponseEntity.notFound().build();}
}
