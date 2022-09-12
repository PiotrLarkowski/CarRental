package com.example.carrental.service.RentalOfficeService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import com.example.carrental.domain.RentalOffice.CarRentalOfficeException;
import com.example.carrental.domain.User.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface CarRentalOfficeService {

    CarRentalOffice getCarRentalOfficeById(String id);

    List<CarRentalOffice> getAllCarRentalOffices();

    List<CarRentalOffice> findCarRentalOfficeByLocalDateTimeOfRent(LocalDateTime dateTime) throws CarRentalOfficeException;

    boolean rentACar(Long userId, Long carId) throws Exception;

    boolean returnACar(Long userId, Long carId) throws Exception;


}
