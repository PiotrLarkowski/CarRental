package com.example.carrental;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto;
import com.example.carrental.service.CarsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CarFactory implements CommandLineRunner {

    private final CarsService carsService;

    public CarFactory(CarsService carsService) {
        this.carsService = carsService;
    }

    @Override
    public void run(String... args) throws Exception {
        CarDto car = new CarDto("volvo", "XC90", "combi", 1990, "red", 19999, CarStatus.BUSY, BigDecimal.valueOf(250000L));
        carsService.createCar(car);
    }
}
