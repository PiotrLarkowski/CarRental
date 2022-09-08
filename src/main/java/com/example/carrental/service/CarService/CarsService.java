package com.example.carrental.service.CarService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto.CarDto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface CarsService {

    Car createCar(CarDto carDto);

    void updateCar(CarDto carDto, String id) throws Exception;

    List<Car> getAllCars();

    Car getCarById(String id) throws CarException;

    void deleteCarById(String id) throws CarException;
    List<Car> filterCarsByMark(String mark);

    List<Car> filterCarsByDayPrice(BigDecimal from, BigDecimal to);

    List<Car> filterCarsByYearOfProduction(int yearOfProduction);

    List<Car> filterCarsByBodyType(String bodyType);

    List<Car> filterCarsByCarStatus(CarStatus carStatus);

}
