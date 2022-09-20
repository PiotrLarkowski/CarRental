package com.example.carrental.repository;

import com.example.carrental.domain.Car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Car, Long> {
    Car findCarById(Long id);
}
