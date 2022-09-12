package com.example.carrental;

import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto.CarDto;
import com.example.carrental.service.CarService.CarsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CarFactory implements CommandLineRunner {

    private final CarsService carsService;

    public CarFactory(CarsService carsService) {
        this.carsService = carsService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<CarDto> listCarExample = new ArrayList<>(Arrays.asList(
                new CarDto("volvo", "XC90", "combi", 1990, "red", 19999, CarStatus.IN_REPAIR, BigDecimal.valueOf(250000L), new ArrayList<>()),
                new CarDto("vw", "passat", "combi", 1995, "green", 15999, CarStatus.AVAILABLE, BigDecimal.valueOf(150000L), new ArrayList<>()),
                new CarDto("ford", "mondeo", "combi", 2000, "blue", 100000, CarStatus.AVAILABLE, BigDecimal.valueOf(300000L), new ArrayList<>()),
                new CarDto("fiat", "panda", "sedan", 2005, "red", 100000, CarStatus.BROKEN, BigDecimal.valueOf(150000L), new ArrayList<>()),
                new CarDto("bmw", "3", "sedan", 1958, "black", 150000, CarStatus.RENTED, BigDecimal.valueOf(350000L), new ArrayList<>())
        ));

        for(int i =0; i<listCarExample.size(); i++) {
            carsService.createCar(listCarExample.get(i));
        }
    }
}
