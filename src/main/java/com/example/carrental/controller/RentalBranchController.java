package com.example.carrental.controller;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.RentalBranch.RentalBranch;
import com.example.carrental.domainDto.RentalBranchDto.RentalBranchDto;
import com.example.carrental.service.RentalBranchService.RentalBranchSerwis;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/rentalBranch")
public class RentalBranchController {

    private final RentalBranchSerwis rentalBranchService;

    public RentalBranchController(RentalBranchSerwis rentalBranchService) {
        this.rentalBranchService = rentalBranchService;
    }
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentalBranch createRentalBranch(@RequestBody @Valid RentalBranchDto rentalBranchDto){
        return rentalBranchService.createRentalBranch(rentalBranchDto);
    }
    @GetMapping(path = "/allCars/{id}")
    public List<Car> getAllCarsRentalBranch(@PathVariable Long id) throws Exception {
        return rentalBranchService.getAllCarsFromBranch(id);
    }

    @GetMapping
    public List<RentalBranch> getAllRentalBranch(){
        return rentalBranchService.getAllRentalBranch();
    }

    @GetMapping(path = "/{id}")
    public RentalBranch getRentalBranchById(@PathVariable Long id) throws Exception {
        return rentalBranchService.getRentalBranchById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(path="/{id}")
    public void deleteRentalBranch(@PathVariable Long id) throws Exception {
        rentalBranchService.deleteRentalBranch(id);
    }


}
