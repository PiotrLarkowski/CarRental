package com.example.carrental.controller.CarRentalOffice;

import com.example.carrental.repository.CarsRentalOfficeRepository;
import com.example.carrental.service.CarService.CarsService;
import com.example.carrental.service.RentalOfficeService.CarRentalOfficeService;
import com.example.carrental.service.UserService.UsersService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/carRentalOffice")
public class CarRentalOfficeContoller {

    private final UsersService usersService;

    private final CarsService carsService;

    private final CarRentalOfficeService carRentalOfficeService;

    public CarRentalOfficeContoller(UsersService usersService, CarsService carsService, CarsRentalOfficeRepository carsRentalOfficeRepository, CarRentalOfficeService carRentalOfficeService) {
        this.usersService = usersService;
        this.carsService = carsService;
        this.carRentalOfficeService = carRentalOfficeService;
    }

    @PutMapping(path = "/rentCar")
    public void userRentCar(@RequestParam Long userId, @RequestParam Long carId) throws Exception{
        carRentalOfficeService.rentACar(userId, carId);
    }

    @PutMapping(path ="/returnCar")
    public void userReturnCar(@RequestParam Long userId, @RequestParam Long carId) throws Exception{
        carRentalOfficeService.returnACar(userId,carId);
    }
}
