package com.example.carrental.service.CarService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto.CarDto;
import com.example.carrental.repository.CarsRepository;
import com.example.carrental.repository.RentalBranchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.junit.jupiter.api.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarsServiceImplTest {

    @Mock
    private CarsService carsService;


    @Test
    public void shouldUpdateACor() throws Exception {
        //given
        Long carId = 1L;
        Car car = new Car(carId, 1L, "Mark", "Model", "bodyType", 1990,
                "Red", 3, CarStatus.AVAILABLE, BigDecimal.valueOf(15));
        Mockito.when(carsService.getCarById(carId)).thenReturn(car);


        CarDto carDto = new CarDto(carId, "Mark", "Model", "bodyType", 1990,
                "Green", 3, CarStatus.AVAILABLE, BigDecimal.valueOf(15));

        //when
        carsService.updateCar(carDto,carId);

        //then
        assertEquals("","");
    }

}