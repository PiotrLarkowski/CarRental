package com.example.carrental.repository;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Collection;

public interface CarsRentalOfficeRepository extends JpaRepository<CarRentalOffice, String> {
}
