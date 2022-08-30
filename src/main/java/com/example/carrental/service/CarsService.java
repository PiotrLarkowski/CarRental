package com.example.carrental.service;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public interface CarsService {

    Car createCar(CarDto carDto);

    Optional<Car> updateCar(CarDto carDto, String id);

    Collection<Car> getAllCars();

    Collection<Car> filterCarsByCarStatus(CarStatus carStatus);

    Collection<Car> filterCarsByBodyType(String bodyType);

    Collection<Car> filterCarsByDayPrice(BigDecimal price);

    Optional<Car> getCarById(String id);

    Optional<Car> deleteCarById(String id);

}