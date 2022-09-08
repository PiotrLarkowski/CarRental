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
        System.out.println("CREATING a NEW CAR.");
        Car car = new Car(UUID.randomUUID().toString(), carDto.getMark(), carDto.getModel(), carDto.getBodyType(), carDto.getYearOfProduction(),
                carDto.getColour(), carDto.getRun(), carDto.getCarStatus(), carDto.getDayPrice());
        carsRepository.save(car);
        return car;
    }

    @Override
    public void updateCar(CarDto carDto, String id) throws Exception{
        System.out.println("UPDATING car");
        Car carToUpdate = getCarById(id);

        Car car = new Car(carToUpdate.getId(), carDto.getMark(), carDto.getModel(), carDto.getBodyType(), carDto.getYearOfProduction(),
                carDto.getColour(), carDto.getRun(), carDto.getCarStatus(), carDto.getDayPrice());

        carsRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        System.out.println("GET ALL cars");
        return carsRepository.findAll();
    }

    @Override
    public Car getCarById(String id) throws CarException {
        System.out.println("FILTER_BY_ID cars");

        return validCarId(id);


//        Optional<String> optionalID = Optional.of(id);
//        if(optionalID.isPresent()) {
//            return carsRepository.findById(id);
//        }
//        return Optional.ofNullable(null);
    }

    @Override
    public List<Car> filterCarsByCarStatus(CarStatus carStatus) {
        System.out.println("FILTER_BY_STATUS cars");
        return carsRepository.findAll().stream()
                .filter(car -> car.getCarStatus().equals(carStatus))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> filterCarsByBodyType(String bodyType) {
        System.out.println("FILTER_BY_BODY_TYPE cars");
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
        System.out.println("FILTER_BY_PRICE cars");
        return carsRepository.findAll().stream()
                .filter(car -> isInRange(car.getDayPrice(), from, to))
                .collect(Collectors.toList());
    }

    private boolean isInRange(BigDecimal price, BigDecimal from, BigDecimal to) {
        return price.compareTo(from)  > 0  && price.compareTo(to) < 0;
    }


    @Override
    public List<Car> filterCarsByMark(String mark) {
        return Optional.of(mark)
                .map (getAllCarsByMark (mark))
                .orElse(carsRepository.findAll());
    }

    private Function<String, List<Car>> getAllCarsByMark(String mark) {
        return m -> carsRepository.findAll().stream()
                .filter(car -> car.getMark().equals(mark))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCarById(String id) throws CarException {
        Car car = validCarId(id);
        System.out.println("DELETE car");
        carsRepository.deleteById(car.getId());
    }

    private Car validCarId(String id) throws CarException {
       return carsRepository.findById(id).orElseThrow(() -> new CarException("couldn't find specific id"));
    }

}
