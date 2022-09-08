package com.example.carrental.service.CarService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto.CarDto;
import com.example.carrental.repository.CarsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CarsServiceImpl implements CarsService {

    private final CarsRepository carsRepository;

    public CarsServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public Car createCar(CarDto carDto) {
        Car car = new Car(UUID.randomUUID().toString(), carDto.getMark(), carDto.getModel(), carDto.getBodyType(), carDto.getYearOfProduction(),
                carDto.getColour(), carDto.getRun(), carDto.getCarStatus(), carDto.getDayPrice());
        carsRepository.save(car);
        return car;
    }

    @Override
    public void updateCar(CarDto carDto, String id) throws Exception{
        Car carToUpdate = getCarById(id);
        Car car = new Car(carToUpdate.getId(), carDto.getMark(), carDto.getModel(), carDto.getBodyType(), carDto.getYearOfProduction(),
                carDto.getColour(), carDto.getRun(), carDto.getCarStatus(), carDto.getDayPrice());
        carsRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carsRepository.findAll();
    }

    @Override
    public Car getCarById(String id) throws CarException {
        return validCarId(id);
    }

    @Override
    public List<Car> filterCarsByCarStatus(CarStatus carStatus) {
        return carsRepository.findAll().stream()
                .filter(car -> car.getCarStatus().equals(carStatus))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> filterCarsByBodyType(String bodyType) {
        return carsRepository.findAll().stream()
                .filter(car -> car.getBodyType().equals(bodyType))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> filterCarsByYearOfProduction(int yearOfProduction) {
        return carsRepository.findAll().stream()
                .filter(car -> car.getYearOfProduction() == yearOfProduction)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> filterCarsByDayPrice(BigDecimal from, BigDecimal to) {
        return carsRepository.findAll().stream()
                .filter(car -> isInRange(car.getDayPrice(), from, to))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> filterCarsByMark(String mark) {
        return carsRepository.findAll().stream()
                .filter(car -> car.getMark().equals(mark))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCarById(String id) throws CarException {
        Car car = validCarId(id);
        System.out.println("DELETE car");
        carsRepository.deleteById(car.getId());
    }

    private boolean isInRange(BigDecimal price, BigDecimal from, BigDecimal to) {
        return price.compareTo(from)  > 0  && price.compareTo(to) < 0;
    }

    private Car validCarId(String id) throws CarException {
       return carsRepository.findById(id).orElseThrow(() -> new CarException("Couldn't find specific id"));
    }

}
