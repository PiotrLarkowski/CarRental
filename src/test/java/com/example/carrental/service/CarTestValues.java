package com.example.carrental.service;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domain.Income.Income;
import com.example.carrental.domain.User.CarRentalUser;
import com.example.carrental.domainDto.CarDto.CarDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CarTestValues {

    private static final Long carId = 1L;

    public static final Car availableCar = new Car(carId, 1L, "Mark", "Model", "bodyType", 1990,
            "Red", 3, CarStatus.AVAILABLE, BigDecimal.valueOf(15));

    public static final Car secondAvailableCar = new Car(2L, 1L, "Mark", "Model", "bodyType", 1990,
            "Red", 3, CarStatus.AVAILABLE, BigDecimal.valueOf(15));
    public static final Car brokenCar = new Car(carId, 1L, "Mark", "Model", "bodyType", 1990,
            "Red", 3, CarStatus.BROKEN, BigDecimal.valueOf(15));

    public static final CarDto availableCarDto = new CarDto(carId, "Mark", "Model", "bodyType", 1990,
            "Red", 3, CarStatus.AVAILABLE, BigDecimal.valueOf(15));

}
