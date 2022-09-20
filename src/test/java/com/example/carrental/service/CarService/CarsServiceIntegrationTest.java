package com.example.carrental.service.CarService;

import com.example.carrental.repository.CarsRepository;
import com.example.carrental.service.CarTestValues;
import com.example.carrental.repository.RentalBranchRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("Test")
class CarsServiceIntegrationTest {

    @Mock
    CarsRepository carsRepository;

    @Mock
    RentalBranchRepository rentalBranchRepository;

    @Test
    public void shouldCreateProperObjectCar() throws Exception {
        //GIVEN
        CarsServiceImpl carsService = new CarsServiceImpl(carsRepository, rentalBranchRepository);
//        Mockito.when(RentalBranch.getCars()).thenReturn
        //WHEN
        carsService.createCar(CarTestValues.availableCarDto, 1L);

        //THEN

    }

}