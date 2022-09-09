package com.example.carrental.repository;

import com.example.carrental.domain.Car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Car, String> {
    void deleteById(Long id);

    Car findCarById(Long id);
}
