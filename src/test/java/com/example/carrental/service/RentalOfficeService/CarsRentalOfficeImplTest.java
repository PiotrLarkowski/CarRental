package com.example.carrental.service.RentalOfficeService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domain.User.CarRentalUser;
import com.example.carrental.repository.*;
import com.example.carrental.service.CarService.CarsServiceImpl;
import com.example.carrental.service.IncomeService.IncomesService;
import com.example.carrental.service.UserService.UsersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
@ExtendWith(MockitoExtension.class)
class CarsRentalOfficeImplTest {

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

    @Mock
    private IncomeRepository incomeRepository;

    @Test
    public void shouldAllowUserToRentACar() throws Exception {
        //given

        Long carId = 1L;
        Long userId = 1L;
        Long carRentalBranchId = 1L;

        CarsServiceImpl carService = new CarsServiceImpl(carsRepository, rentalBranchRepository);

        UsersServiceImpl usersService = new UsersServiceImpl(userRepository);

        CarsRentalOfficeImpl carsRentalOfficeService = new CarsRentalOfficeImpl(carsRentalOfficeRepository,carService,usersService,incomeService,incomeRepository);

        Mockito.when(carsRepository.findCarById(carId)).thenReturn(new Car(carId, carRentalBranchId, "Mark", "Model", "bodyType", 1990,
                "Red", 3, CarStatus.AVAILABLE, BigDecimal.valueOf(15)));

        Mockito.when(userRepository.findUserById(userId)).thenReturn(new CarRentalUser(userId, "Login",
                "Password","name","lastName","aa@op.pl","address",
                null,"USER","ACTIVE"));

        //when
        carsRentalOfficeService.rentACar(userId, carId);

        //then
        verify(userRepository,times(1)).save(Mockito.any());

        verify(carsRepository,times(1)).save(Mockito.any());

        verify(carsRentalOfficeRepository,times(1)).save(Mockito.any());


    }

    @Test
    public void shouldNotAllowUserToRentACarBecouseUsrHavelreadyRentACar() throws Exception {
        //given

        Long carId = 1L;
        Long userId = 1L;
        Long carRentalBranchId = 1L;

        CarsServiceImpl carService = new CarsServiceImpl(carsRepository, rentalBranchRepository);

        UsersServiceImpl usersService = new UsersServiceImpl(userRepository);

        CarsRentalOfficeImpl carsRentalOfficeService = new CarsRentalOfficeImpl(carsRentalOfficeRepository,carService,usersService,incomeService,incomeRepository);

        Mockito.when(carsRepository.findCarById(carId)).thenReturn(new Car(carId, carRentalBranchId, "Mark", "Model", "bodyType", 1990,
                "Red", 3, CarStatus.AVAILABLE, BigDecimal.valueOf(15)));

        Mockito.when(userRepository.findUserById(userId)).thenReturn(new CarRentalUser(userId, "Login",
                "Password","name","lastName","aa@op.pl","address",
                carId,
                "USER","ACTIVE"));

        //when
        carsRentalOfficeService.rentACar(userId, carId);

        //then
        verify(userRepository,times(0)).save(Mockito.any());

        verify(carsRepository,times(0)).save(Mockito.any());

        verify(carsRentalOfficeRepository,times(0)).save(Mockito.any());


    }
}