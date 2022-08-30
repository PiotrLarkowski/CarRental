package com.example.carrental.repository;

import com.example.carrental.domain.Car;
import com.example.carrental.domain.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Collection;

public interface CarsRepository extends JpaRepository<Car, String> {

    Collection<Car> filterCarsByStatus(CarStatus carStatus);
    Collection<Car> filterCarsByBodyType(String bodyType);

    Collection<Car> filterCarsByPrice(BigDecimal price);


}
