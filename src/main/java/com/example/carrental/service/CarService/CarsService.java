package com.example.carrental.service.CarService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto.CarDto;

import java.math.BigDecimal;
import java.util.Collection;

public interface CarsService {

    Car createCar(CarDto carDto);

    Car updateCar(CarDto carDto, String id) throws Exception;

    Collection<Car> getAllCars();

    Collection<Car> filterCarsByCarStatus(CarStatus carStatus);

    Collection<Car> filterCarsByBodyType(String bodyType);

    Collection<Car> filterCarsByDayPrice(BigDecimal price);

    Car getCarById(String id) throws CarException;

    void deleteCarById(String id) throws CarException;

}
