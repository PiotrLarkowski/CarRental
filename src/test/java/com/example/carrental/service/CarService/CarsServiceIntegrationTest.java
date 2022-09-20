package com.example.carrental.service.CarService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.repository.CarsRepository;
import com.example.carrental.service.CarTestValues;
import com.example.carrental.repository.RentalBranchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CarsServiceIntegrationTest {

    @Autowired
    CarsRepository carsRepository;

    @Autowired
    RentalBranchRepository rentalBranchRepository;

    @Test
    public void shouldVeryfyThetCarIsAssignedToRentalBranchAfterCreation() throws Exception {
        //GIVEN
        CarsServiceImpl carsService = new CarsServiceImpl(carsRepository, rentalBranchRepository);
        //WHEN
        Car car = carsService.createCar(CarTestValues.availableCarDto, 1L);

        //THEN
        assertThat(carsRepository.findCarById(car.getId())).isNotNull();
        assertThat(rentalBranchRepository.findRentalBranchById(1L).getCars().contains(car)).isTrue();
    }

}