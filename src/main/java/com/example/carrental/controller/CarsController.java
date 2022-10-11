package com.example.carrental.controller;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto.CarDto;
import com.example.carrental.service.CarService.CarsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/car")
public class CarsController {

    private final CarsService carsService;

    public CarsController(CarsService carsService) {
        System.out.println("CarsService " + carsService);
        this.carsService = carsService;
    }

    @Secured({"ROLE_MODERATOR","ROLE_ADMIN"})
    @PostMapping(path ="/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody @Valid CarDto carDto, @PathVariable Long id) throws Exception {
        return carsService.createCar(carDto, id);
    }

    @Secured({"ROLE_MODERATOR","ROLE_ADMIN"})
    @PutMapping(path = "/{id}")
    public void updateCar(@RequestBody CarDto carDto, @PathVariable Long id) throws Exception {
        carsService.updateCar(carDto, id);
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carsService.getAllCars();
    }

    @GetMapping(path = "/filterByCarStatus/{carStatus}")
    public List<Car> filterCarsByStatus(@PathVariable @Valid CarStatus carStatus) {
        return carsService.filterCarsByCarStatus(carStatus);
    }

    @GetMapping(path = "/filterByBodyType/{bodyType}")
    public List<Car> filterCarsByBodyType(@PathVariable String bodyType) {
        return carsService.filterCarsByBodyType(bodyType);
    }

    @GetMapping(path = "/filterByMark/{Mark}")
    public List<Car> filterCarsByMark(@PathVariable String Mark) {
        return carsService.filterCarsByMark(Mark);
    }

    @GetMapping(path = "/filterByYearOfProduction/{yearOfProduction}")
    public List<Car> filterCarsByYearOfProduction(@PathVariable int yearOfProduction) {
        return carsService.filterCarsByYearOfProduction(yearOfProduction);
    }

    @GetMapping(path = "/filterByPrice")
    public List<Car> filterCarsByPrice(@RequestParam BigDecimal from, @RequestParam BigDecimal to) {
        return carsService.filterCarsByDayPrice(from, to);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) throws CarException {
        return new ResponseEntity<>(carsService.getCarById(id), HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(path = "/{id}")
    public void deleteCar(@PathVariable Long id) throws CarException {
        carsService.deleteCarById(id);
    }

}
