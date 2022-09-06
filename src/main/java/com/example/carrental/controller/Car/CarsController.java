package com.example.carrental.controller.Car;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto.CarDto;
import com.example.carrental.service.CarService.CarsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/car")
public class CarsController {

    private final CarsService carsService;


    public CarsController(CarsService carsService) {
        System.out.println("CarsService " + carsService);
        this.carsService = carsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody @Valid CarDto carDto) {
        return carsService.createCar(carDto);
    }

    @PutMapping(path = "/{id}")
    public Car updateCar(@RequestBody CarDto carDto, @PathVariable String id) throws Exception {
        return carsService.updateCar(carDto, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Collection<Car> getAllCars() {
        return carsService.getAllCars();
    }

//    @GetMapping(path = "/filterByCarStatus/{carStatus}")
//    public Collection<Car> filterCarsByStatus(@PathVariable @Valid CarStatus carStatus) {
//        return carsService.filterCarsByCarStatus(carStatus);
//    }
//
//    @GetMapping(path = "/filterByBodyType/{bodyType}")
//    public Collection<Car> filterCarsByBodyType(@PathVariable String bodyType) {
//        return carsService.filterCarsByBodyType(bodyType);
//    }
//
//    @GetMapping(path = "/filterByPrice/{price}")
//    public Collection<Car> filterCarsByPrice(@PathVariable BigDecimal price) {
//        return carsService.filterCarsByDayPrice(price);
//    }

//    @GetMapping(path = "/{id}")
//    public ResponseEntity<?> getCarById(@PathVariable String id) {
//        if(id.isEmpty()) {
//            return new OwnExceptionHandler().getResponseHttpNotFound();
//        }
//        return new ResponseEntity<>(carsService.getCarById(id).get(), HttpStatus.OK);
//    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable String id) throws CarException {
        return new ResponseEntity<>(carsService.getCarById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable String id) throws CarException {
        carsService.deleteCarById(id);
    }



}
