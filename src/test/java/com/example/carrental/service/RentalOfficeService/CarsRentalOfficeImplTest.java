package com.example.carrental.service.RentalOfficeService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domain.RentalOffice.CarRentalOfficeException;
import com.example.carrental.domain.User.CarRentalUser;
import com.example.carrental.repository.*;
import com.example.carrental.service.CarService.CarsServiceImpl;
import com.example.carrental.service.CarTestValues;
import com.example.carrental.service.IncomeService.IncomesService;
import com.example.carrental.repository.RentalBranchRepository.*;
import com.example.carrental.service.UserService.UsersServiceImpl;
import com.example.carrental.service.UserTestValues;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class CarsRentalOfficeImplTest {

    private Long carId = 1L;
    private Long userId = 2L;

    @Mock
    private CarsRentalOfficeRepository carsRentalOfficeRepository;

    @Mock
    private CarsRepository carsRepository;
    @Mock
    private RentalBranchRepository rentalBranchRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IncomesService incomeService;


    @Test
    public void shouldAllowUserToRentACar() throws Exception {
        //GIVEN

        CarsServiceImpl carService = new CarsServiceImpl(carsRepository, rentalBranchRepository);

        UsersServiceImpl usersService = new UsersServiceImpl(userRepository);

        CarsRentalOfficeImpl carsRentalOfficeService = new CarsRentalOfficeImpl(carsRentalOfficeRepository,carService,usersService,incomeService);

        Mockito.when(carsRepository.findCarById(carId)).thenReturn(CarTestValues.availableCar);

        Mockito.when(userRepository.findUserById(userId)).thenReturn(UserTestValues.userWithNoCarRented);

        //WHEN
        carsRentalOfficeService.rentACar(userId, carId);

        //THEN
        verify(userRepository,times(1)).save(Mockito.any());

        verify(carsRepository,times(1)).save(Mockito.any());

        verify(carsRentalOfficeRepository,times(1)).save(Mockito.any());


    }

    @Test
    public void shouldNotAllowUserToRentAnotherCarBecauseUserHaveAlreadyRentACar() throws Exception {
        //GIVEN

        Long carThatUserWantToRent = 3L;

        CarsServiceImpl carService = new CarsServiceImpl(carsRepository, rentalBranchRepository);

        UsersServiceImpl usersService = new UsersServiceImpl(userRepository);

        CarsRentalOfficeImpl carsRentalOfficeService = new CarsRentalOfficeImpl(carsRentalOfficeRepository,carService,usersService,incomeService);

        Mockito.when(carsRepository.findCarById(carThatUserWantToRent)).thenReturn(CarTestValues.availableCar);

        Mockito.when(userRepository.findUserById(userId)).thenReturn(UserTestValues.userWithCarRented);

        //WHEN
        assertThrows(CarRentalOfficeException.class, () -> {
            carsRentalOfficeService.rentACar(userId, carThatUserWantToRent);
        });

        //THEN

        verify(userRepository,times(0)).save(Mockito.any());
        verify(carsRepository,times(0)).save(Mockito.any());
        verify(carsRentalOfficeRepository,times(0)).save(Mockito.any());

    }

    @Test
    public void shouldNotAllowUserToRentACarWitchIsNotAllowToRent() {
        //GIVEN

        CarsServiceImpl carService = new CarsServiceImpl(carsRepository, rentalBranchRepository);

        UsersServiceImpl usersService = new UsersServiceImpl(userRepository);

        CarsRentalOfficeImpl carsRentalOfficeService = new CarsRentalOfficeImpl(carsRentalOfficeRepository,carService,usersService,incomeService);

        Mockito.when(carsRepository.findCarById(carId)).thenReturn(CarTestValues.brokenCar);

        Mockito.when(userRepository.findUserById(userId)).thenReturn(UserTestValues.userWithNoCarRented);

        //WHEN
        assertThrows(CarException.class, () -> {
            carsRentalOfficeService.rentACar(userId, carId);
        });

        //THEN

        verify(userRepository,times(0)).save(Mockito.any());
        verify(carsRepository,times(0)).save(Mockito.any());
        verify(carsRentalOfficeRepository,times(0)).save(Mockito.any());

    }
}