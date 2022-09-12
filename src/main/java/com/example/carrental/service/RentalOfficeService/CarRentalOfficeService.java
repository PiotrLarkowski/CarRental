package com.example.carrental.service.RentalOfficeService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import com.example.carrental.domain.RentalOffice.CarRentalOfficeException;
import com.example.carrental.domain.User.User;
import com.example.carrental.domainDto.RentalOffice.CarRentalOfficeDto;
import com.example.carrental.domainDto.RentalOffice.CarRentalOfficeList;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface CarRentalOfficeService {

    CarRentalOffice getCarRentalOfficeById(Long id);

    List<CarRentalOfficeList> getAllCarRentalOffices();

    List<CarRentalOffice> findCarRentalOfficeByLocalDateTimeOfRent(LocalDateTime dateTime) throws CarRentalOfficeException;

    List<CarRentalOfficeList> findAllDto();

    boolean rentACar(Long userId, Long carId) throws Exception;

    boolean returnACar(Long userId, Long carId) throws Exception;


}
