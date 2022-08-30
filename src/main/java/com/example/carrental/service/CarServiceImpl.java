package com.example.carrental.service;

import com.example.carrental.domain.Car;
import com.example.carrental.domain.CarStatus;
import com.example.carrental.domainDto.CarDto;
import com.example.carrental.repository.CarsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {

    private final CarsRepository carsRepository;

    public CarServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public Car createCar(CarDto carDto) {
        System.out.println("CREATING a NEW CAR.");
        Car car = new Car(UUID.randomUUID().toString(), carDto.getMark(), carDto.getModel(), carDto.getBodyType(), carDto.getYearOfProduction(),
                carDto.getColour(), carDto.getRun(), carDto.getCarStatus(), carDto.getDayPrice());
        carsRepository.save(car);
        return car;
    }

    @Override
    public Optional<Car> updateCar(CarDto carDto, String id) {
        System.out.println("UPDATING car");
        Optional<Car> carToUpdate = getCarById(id);
        if(carToUpdate.isEmpty()) {
            return carToUpdate;
        }
        Car foundCar = carToUpdate.get();
        Car updatedCar = new Car(foundCar.getId(), carDto.getMark(), carDto.getModel(), carDto.getBodyType(), carDto.getYearOfProduction(),
                carDto.getColour(), carDto.getRun(), carDto.getCarStatus(), carDto.getDayPrice());
        carsRepository.save(updatedCar);
        return Optional.of(updatedCar);
    }

    @Override
    public Collection<Car> getAllCar() {
        System.out.println("GET ALL cars");
        return carsRepository.findAll();
    }

    @Override
    public Collection<Car> filterCarsByStatus(CarStatus carStatus) {
        System.out.println("FILTER_BY_STATUS cars");
        Optional<CarStatus> optionalCarStatus = Optional.of(carStatus);
        if (optionalCarStatus.isPresent()) {
            return carsRepository.filterCarsByStatus(carStatus);
        }
        return carsRepository.findAll();
    }

    @Override
    public Collection<Car> filterCarsByBodyType(String bodyType) {
        System.out.println("FILTER_BY_BODY_TYPE cars");
        Optional<String> optionalBodyType = Optional.of(bodyType);
        if(optionalBodyType.isPresent()) {
            return carsRepository.filterCarsByBodyType(bodyType);
        }
        return carsRepository.findAll();
    }

    @Override
    public Collection<Car> filterCarsByPrice(BigDecimal price) {
        System.out.println("FILTER_BY_PRICE cars");
        Optional<BigDecimal> optionalPrice = Optional.of(price);
        if(optionalPrice.isPresent()) {
            return carsRepository.filterCarsByPrice(price);
        }
        return carsRepository.findAll();
    }

    @Override
    public Optional<Car> getCarById(String id) {
        System.out.println("FILTER_BY_ID cars");
        Optional<String> optionalID = Optional.of(id);
        if(optionalID.isPresent()) {
            return carsRepository.findById(id);
        }
        return carsRepository.findById(id);
    }

    @Override
    public Optional<Car> deleteCarById(String id) {
        Optional<String> optionalID = Optional.of(id);
        if(optionalID.isPresent()) {
            carsRepository.deleteById(id);
        }
        return Optional.of(null);
    }
}
