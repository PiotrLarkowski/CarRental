package com.example.carrental.service.RentalOfficeService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import com.example.carrental.domain.User.User;
import com.example.carrental.repository.CarsRentalOfficeRepository;
import com.example.carrental.service.CarService.CarsService;
import com.example.carrental.service.UserService.UsersService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarsRentalOfficeImpl implements CarRentalOfficeService {

    private final CarsRentalOfficeRepository carsRentalOfficeRepository;

    private final CarsService carsService;

    private final UsersService usersService;

    public CarsRentalOfficeImpl(CarsRentalOfficeRepository carsRentalOfficeRepository, CarsService carsService, UsersService usersService) {
        this.carsRentalOfficeRepository = carsRentalOfficeRepository;
        this.carsService = carsService;
        this.usersService = usersService;
    }

    @Override
    public CarRentalOffice getCarRentalOfficeById(String id) {
        return null;
    }

    @Override
    public Collection<CarRentalOffice> getAllCarRentalOffices() {
        return null;
    }

    @Override
    public CarRentalOffice getCarRentalOfficeByDateTime(LocalDateTime dateTime) {
        return null;
    }

    @Override
    public void rentACar(String userId, String carId) throws Exception{
        changeCarStatusByUser(userId, carId, CarStatus.RENTED);
    }

    @Override
    public void returnACar(String userId, String carId) throws Exception {
        changeCarStatusByUser(userId, carId, CarStatus.AVAILABLE);
    }

    @Override
    public Collection<Car> filterCarsByCarStatus(CarStatus carStatus) {
        System.out.println("FILTER_BY_STATUS cars");
        Optional<CarStatus> optionalCarStatus = Optional.of(carStatus);
        if (optionalCarStatus.isPresent()) {
            return carsService.getAllCars().stream()
                    .filter(car -> car.getCarStatus().equals(carStatus))
                    .collect(Collectors.toList());
        }
        return carsService.getAllCars();
    }

    @Override
    public Collection<Car> filterCarsByBodyType(String bodyType) {
        System.out.println("FILTER_BY_BODY_TYPE cars");
        Optional<String> optionalBodyType = Optional.of(bodyType);
        if(optionalBodyType.isPresent()) {
            return carsService.getAllCars().stream()
                    .filter(car -> car.getBodyType().equals(bodyType))
                    .collect(Collectors.toList());
        }
        return carsService.getAllCars();
    }

    @Override
    public Collection<Car> filterCarsByDayPrice(BigDecimal price) {
        System.out.println("FILTER_BY_PRICE cars");
        Optional<BigDecimal> optionalPrice = Optional.of(price);
        if(optionalPrice.isPresent()) {
            return carsService.getAllCars().stream()
                    .filter(car -> car.getDayPrice().compareTo(price) > 0)
                    .collect(Collectors.toList());
        }
        return carsService.getAllCars();
    }

    @Override
    public Collection<Car> filterCarsByYearOfProduction(int yearOfProduction) {
        return null;
    }

    @Override
    public Collection<Car> filterCarsByMark(String mark) {
        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }


    private Car changeCarStatusByUser(String userId, String carId, CarStatus carStatus) throws Exception {
        User user = getUserById(userId)
                .orElseThrow(() -> new UserException("No client with given ID"));
        if(!user.getUserCarId().equals(null)) {
            new CarException("User has already rented car");
        }
        CarDto carToRent = carsService.getCarById(carId)
                .filter(availableCar -> availableCar.getCarStatus().equals(CarStatus.AVAILABLE))
                .map(carStream -> new CarDto(carStream.getMark(), carStream.getModel(), carStream.getBodyType(),
                        carStream.getYearOfProduction(), carStream.getColour(), carStream.getRun(),
                        carStatus, carStream.getDayPrice()))
                .orElseThrow(() -> new CarException("No car with given ID or car is not available"));
        carsService.updateCar(carToRent, carId);

        if(carStatus.equals(CarStatus.AVAILABLE)){
            user.setUserCarId(carId);
        }else{
            user.setUserCarId("");
        }

        userRepository.save(user);
        return carsService.getCarById(carId).get();
    }



}
