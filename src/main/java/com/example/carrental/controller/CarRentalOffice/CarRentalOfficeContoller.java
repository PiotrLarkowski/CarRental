package com.example.carrental.controller.CarRentalOffice;

import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import com.example.carrental.domainDto.RentalOffice.CarRentalOfficeList;
import com.example.carrental.service.RentalOfficeService.CarRentalOfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path="/carRentalOffice")
public class CarRentalOfficeContoller {

    private final CarRentalOfficeService carRentalOfficeService;

    public CarRentalOfficeContoller(CarRentalOfficeService carRentalOfficeService) {
        this.carRentalOfficeService = carRentalOfficeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarRentalOffice> getAllCarRentalOffice(){
        return carRentalOfficeService.getAllCarRentalOffices();
    }

    @GetMapping(path="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarRentalOffice getCarRentalOfficeById(@PathVariable @Valid Long id){
        return carRentalOfficeService.getCarRentalOfficeById(id);
    }

    @GetMapping(path = "/dateTime/{localDateTime}")
    @ResponseStatus(HttpStatus.OK)
    public List<CarRentalOffice> getCarRentalOfficeByDateTime(@PathVariable LocalDateTime localDateTime){
        return carRentalOfficeService.findCarRentalOfficeByLocalDateTimeOfRent(localDateTime);
    }

    @PutMapping(path ="/returnCar")
    public void userReturnCar(@RequestParam Long userId, @RequestParam Long carId, @RequestParam Long carRentalOfficeId) throws Exception{
        carRentalOfficeService.returnACar(userId, carId, carRentalOfficeId);
    }

    @PutMapping(path = "/rentCar")
    public void userRentCar(@RequestParam Long userId, @RequestParam Long carId) throws Exception{ // nie tworzy kolejnego wypożyczenia w bazie danych???
        carRentalOfficeService.rentACar(userId, carId);
    }
}
