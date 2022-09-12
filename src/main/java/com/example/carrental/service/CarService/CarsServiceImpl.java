package com.example.carrental.service.CarService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domainDto.CarDto.CarDto;
import com.example.carrental.domainDto.CarDto.CarDtoNoList;
import com.example.carrental.repository.CarsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarsServiceImpl implements CarsService {

    private final CarsRepository carsRepository;

    public CarsServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public Car createCar(CarDto carDto) {
        Car car = new Car(0L, carDto.getMark(), carDto.getModel(), carDto.getBodyType(), carDto.getYearOfProduction(),
                carDto.getColour(), carDto.getRun(), carDto.getCarStatus(), carDto.getDayPrice(), new ArrayList<>());
        carsRepository.save(car);
        return car;
    }

    @Override
    public void updateCar(CarDto carDto, Long id) throws Exception{
        Car carToUpdate = getCarById(id);
        Car car = new Car(carToUpdate.getId(), carDto.getMark(), carDto.getModel(), carDto.getBodyType(), carDto.getYearOfProduction(),
                carDto.getColour(), carDto.getRun(), carDto.getCarStatus(), carDto.getDayPrice(), new ArrayList<>());
        carsRepository.save(car);
    }

    @Override
    public List<CarDtoNoList> getAllCars() {
        return carsRepository.findAll().stream()
                .map(car -> CarDtoNoList.builder()
                        .id(car.getId())
                        .mark(car.getMark())
                        .model(car.getModel())
                        .bodyType(car.getBodyType())
                        .yearOfProduction(car.getYearOfProduction())
                        .colour(car.getColour())
                        .run(car.getRun())
                        .carStatus(car.getCarStatus())
                        .dayPrice(car.getDayPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Car getCarById(Long id) throws CarException {
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
    public void deleteCarById(Long id) throws CarException {
        validCarId(id);
        carsRepository.deleteById(id);
    }

    private boolean isInRange(BigDecimal price, BigDecimal from, BigDecimal to) {
        return price.compareTo(from)  > 0  && price.compareTo(to) < 0;
    }

    private Car validCarId(Long id) throws CarException {
       return Optional.of(carsRepository.findCarById(id)).orElseThrow(() -> new CarException("Couldn't find specific id"));
    }

}
