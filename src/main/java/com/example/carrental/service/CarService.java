package com.example.carrental.service;

import com.example.carrental.domain.Car;
import com.example.carrental.domain.CarStatus;
import com.example.carrental.domainDto.CarDto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public interface CarService {

    Car createCar(CarDto carDto);

    Optional<Car> updateCar(CarDto carDto, String id);

    Collection<Car> getAllCar();

    Collection<Car> filterCarsByStatus(CarStatus carStatus);

    Collection<Car> filterCarsByBodyType(String bodyType);

    Collection<Car> filterCarsByPrice(BigDecimal price);

    Optional<Car> getCarById(String id);

    Optional<Car> deleteCarById(String id);

}
