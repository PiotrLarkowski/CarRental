package com.example.carrental.controller.RentalBranchController;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.RentalBranch.RentalBranch;
import com.example.carrental.domainDto.RentalBranchDto.RentalBranchDto;
import com.example.carrental.service.RentalBranchService.RentalBranchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rentalBranch")
public class RentalBranchController {

    private final RentalBranchRepository rentalBranchService;

    public RentalBranchController(RentalBranchRepository rentalBranchService) {
        this.rentalBranchService = rentalBranchService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentalBranch createRentalBranch(@RequestBody RentalBranchDto rentalBranchDto){
        return rentalBranchService.createRentalBranch(rentalBranchDto);
    }
    @GetMapping(path = "/allCars/{id}")
    public List<Car> getAllCarsRentalBranch() throws Exception {
        return rentalBranchService.getAllCarsFromBranch(1L);
    }

    @GetMapping
    public List<RentalBranch> getAllRentalBranch(){
        return rentalBranchService.getAllRentalBranch();
    }

    @GetMapping(path = "/{id}")
    public RentalBranch getRentalBranchById(@PathVariable Long id) throws Exception {
        return rentalBranchService.getRentalBranchById(id);
    }

    @DeleteMapping(path="/{id}")
    public void deleteRentalBranch(@PathVariable Long id) throws Exception {
        rentalBranchService.deleteRentalBranch(id);
    }


}
