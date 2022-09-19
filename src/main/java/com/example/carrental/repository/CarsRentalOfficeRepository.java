package com.example.carrental.repository;

import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRentalOfficeRepository extends JpaRepository<CarRentalOffice, String> {

    CarRentalOffice findById(Long id);
}
