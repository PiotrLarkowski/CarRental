package com.example.carrental.controller;

import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import com.example.carrental.service.RentalOfficeService.CarRentalOfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path="/carRentalOffice")
public class CarRentalOfficeContoller {

    private final CarRentalOfficeService carRentalOfficeService;

    public CarRentalOfficeContoller(CarRentalOfficeService carRentalOfficeService) {
        this.carRentalOfficeService = carRentalOfficeService;
    }
    @Secured({"ROLE_MODERATOR","ROLE_ADMIN"})
    @GetMapping
    public List<CarRentalOffice> getAllCarRentalOffice(){
        return carRentalOfficeService.getAllCarRentalOffices();
    }
    @Secured({"ROLE_MODERATOR","ROLE_ADMIN"})
    @GetMapping(path="/{id}")
    public CarRentalOffice getCarRentalOfficeById(@PathVariable @Valid Long id){
        return carRentalOfficeService.getCarRentalOfficeById(id);
    }
    @Secured({"ROLE_MODERATOR","ROLE_ADMIN"})
    @GetMapping(path = "/dateTime/{localDateTime}")
    public List<CarRentalOffice> getCarRentalOfficeByDateTime(@PathVariable LocalDateTime localDateTime){
        return carRentalOfficeService.findCarRentalOfficeByLocalDateTimeOfRent(localDateTime);
    }

    @Secured({"ROLE_MODERATOR","ROLE_ADMIN","ROLE_USER"})
    @PutMapping(path ="/returnCar/{carRentalOfficeId}")
    public void userReturnCar(@PathVariable Long carRentalOfficeId) throws Exception{
        carRentalOfficeService.returnACar(carRentalOfficeId);
    }
    @Secured({"ROLE_MODERATOR","ROLE_ADMIN","ROLE_USER"})
    @PutMapping(path = "/rentCar/{userId}/{carId}")
    public void userRentCar(@PathVariable Long userId, @PathVariable Long carId) throws Exception{
        carRentalOfficeService.rentACar(userId, carId);
    }
}
