package com.example.carrental.service.RentalOfficeService;

import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import com.example.carrental.domain.RentalOffice.CarRentalOfficeException;

import java.time.LocalDateTime;
import java.util.List;

public interface CarRentalOfficeService {

    CarRentalOffice getCarRentalOfficeById(Long id);

    List<CarRentalOffice> getAllCarRentalOffices();

    List<CarRentalOffice> findCarRentalOfficeByLocalDateTimeOfRent(LocalDateTime dateTime) throws CarRentalOfficeException;

    List<CarRentalOffice> findAllDto();

    void rentACar(Long userId, Long carId) throws Exception;

    void returnACar(Long carRentalOfficeId) throws Exception;


}
