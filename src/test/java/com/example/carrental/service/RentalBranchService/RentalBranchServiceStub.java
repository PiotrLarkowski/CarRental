package com.example.carrental.service.RentalBranchService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.RentalBranch.RentalBranch;
import com.example.carrental.domainDto.RentalBranchDto.RentalBranchDto;
import com.example.carrental.service.CarTestValues;
import com.example.carrental.service.RentalBranchTestValues;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Profile("Test")
class RentalBranchServiceStub implements RentalBranchSerwis {

    @Override
    public RentalBranch getRentalBranchById(Long id) throws Exception {
        return RentalBranchTestValues.properRentalBranch;
    }

    @Override
    public RentalBranch createRentalBranch(RentalBranchDto rentalBranchDto) {
        return RentalBranchTestValues.properRentalBranch;
    }

    @Override
    public List<RentalBranch> getAllRentalBranch() {
        return new ArrayList<>(Arrays.asList(RentalBranchTestValues.properRentalBranch,RentalBranchTestValues.properRentalBranch));
    }

    @Override
    public List<Car> getAllCarsFromBranch(Long id) {
        return new ArrayList<>(Arrays.asList(CarTestValues.availableCar,CarTestValues.availableCar));
    }

    @Override
    public void deleteRentalBranch(Long id) {

    }
}