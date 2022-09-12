package com.example.carrental.service.CarService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto.CarDto;
import com.example.carrental.domainDto.CarDto.CarDtoNoList;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface CarsService {

    Car createCar(CarDto carDto);

    void updateCar(CarDto carDto, Long id) throws Exception;

    List<CarDtoNoList> getAllCars();

    Car getCarById(Long id) throws CarException;

    void deleteCarById(Long id) throws CarException;
    List<Car> filterCarsByMark(String mark);

    List<Car> filterCarsByDayPrice(BigDecimal from, BigDecimal to);

    List<Car> filterCarsByYearOfProduction(int yearOfProduction);

    List<Car> filterCarsByBodyType(String bodyType);

    List<Car> filterCarsByCarStatus(CarStatus carStatus);

}
