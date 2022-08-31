package com.example.carrental.service;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarRentException;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto;
import com.example.carrental.repository.CarsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarsServiceImpl implements CarsService {

    private final CarsRepository carsRepository;

    public CarsServiceImpl(CarsRepository carsRepository) {
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
    public Car updateCar(CarDto carDto, String id) throws Exception{
        System.out.println("UPDATING car");
        Car carToUpdate = getCarById(id)
                .map(car -> new Car(car.getId(), carDto.getMark(), carDto.getModel(), carDto.getBodyType(), carDto.getYearOfProduction(),
                carDto.getColour(), carDto.getRun(), carDto.getCarStatus(), carDto.getDayPrice()))
                .orElseThrow(() -> new CarRentException("No car found by get id"));
        carsRepository.save(carToUpdate);
        return carToUpdate;
    }

    @Override
    public Collection<Car> getAllCars() {
        System.out.println("GET ALL cars");
        return carsRepository.findAll();
    }

    @Override
    public Collection<Car> filterCarsByCarStatus(CarStatus carStatus) {
        System.out.println("FILTER_BY_STATUS cars");
        Optional<CarStatus> optionalCarStatus = Optional.of(carStatus);
        if (optionalCarStatus.isPresent()) {
            return carsRepository.findByCarStatus(carStatus);
        }
        return carsRepository.findAll();
    }

    @Override
    public Collection<Car> filterCarsByBodyType(String bodyType) {
        System.out.println("FILTER_BY_BODY_TYPE cars");
        Optional<String> optionalBodyType = Optional.of(bodyType);
        if(optionalBodyType.isPresent()) {
            return carsRepository.findByBodyType(bodyType);
        }
        return carsRepository.findAll();
    }

    @Override
    public Collection<Car> filterCarsByDayPrice(BigDecimal price) {
        System.out.println("FILTER_BY_PRICE cars");
        Optional<BigDecimal> optionalPrice = Optional.of(price);
        if(optionalPrice.isPresent()) {
            return carsRepository.findByDayPrice(price);
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
    public void deleteCarById(String id) {
        Optional<String> optionalID = Optional.of(id);
        if(optionalID.isEmpty()) {
            new CarRentException("No car found by id");
        }
        System.out.println("DELETE car");
        carsRepository.deleteById(id);
    }
}
