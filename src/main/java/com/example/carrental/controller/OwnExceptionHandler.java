package com.example.carrental.controller;

import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.Income.IncomeException;
import com.example.carrental.domain.RentalBranch.RentalBranchException;
import com.example.carrental.domain.RentalOffice.CarRentalOfficeException;
import com.example.carrental.domain.User.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
@Slf4j
@ControllerAdvice
public class OwnExceptionHandler {
    @ExceptionHandler({CarException.class})
    public ResponseEntity<?> getResponseHttpNotFound() {
        log.error("User with id not found!");
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
    public ResponseEntity<?> getResponseHttpRentalNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({IncomeException.class})
    public ResponseEntity<?> getResponseHttpIncomeNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({RentalBranchException.class})
    public ResponseEntity<?> getResponseHttpRentalBranchNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> getCommonException(){
        return ResponseEntity.noContent().build();
    }
}
