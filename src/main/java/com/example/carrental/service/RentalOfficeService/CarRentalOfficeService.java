package com.example.carrental.service.RentalOfficeService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import com.example.carrental.domain.User.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

public interface CarRentalOfficeService {

    CarRentalOffice getCarRentalOfficeById(String id);

    Collection<CarRentalOffice> getAllCarRentalOffices();

    CarRentalOffice getCarRentalOfficeByDateTime (LocalDateTime dateTime);

    void rentACar(String userId, String carId) throws Exception;

    void returnACar(String userId, String carId) throws Exception;

    Collection<Car> filterCarsByCarStatus(CarStatus carStatus);

    Collection<Car> filterCarsByBodyType(String bodyType);

    Collection<Car> filterCarsByDayPrice(BigDecimal price);

    Collection<Car> filterCarsByYearOfProduction(int yearOfProduction);

    Collection<Car> filterCarsByMark(String mark);

    User findUserByLogin(String login) throws Exception;

    User findUserByEmail(String email);





}
