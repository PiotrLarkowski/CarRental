package com.example.carrental.service.RentalOfficeService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domain.RentalOffice.CarRentalOffice;
import com.example.carrental.domain.RentalOffice.CarRentalOfficeException;
import com.example.carrental.domain.User.User;
import com.example.carrental.domain.User.UserException;
import com.example.carrental.domainDto.CarDto.CarDto;
import com.example.carrental.domainDto.UserDto.UserDto;
import com.example.carrental.repository.CarsRentalOfficeRepository;
import com.example.carrental.service.CarService.CarsService;
import com.example.carrental.service.UserService.UsersService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public CarRentalOffice getCarRentalOfficeById(String id) throws CarRentalOfficeException{
        return carsRentalOfficeRepository.findById(id)
                .orElseThrow(()-> new CarRentalOfficeException("Rental not found"));
    }

    @Override
    public List<CarRentalOffice> getAllCarRentalOffices() {
        return carsRentalOfficeRepository.findAll();
    }

    @Override
    public CarRentalOffice getCarRentalOfficeByDateTime(LocalDateTime dateTime) {
        return Optional.of(carsRentalOfficeRepository
                .findCarRentalOfficeByDateTime(dateTime))
                .orElseThrow(() -> new CarRentalOfficeException("Rental not found"));
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
    public List<Car> filterCarsByCarStatus(CarStatus carStatus) {
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
    public List<Car> filterCarsByBodyType(String bodyType) {
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
    public List<Car> filterCarsByDayPrice(BigDecimal from, BigDecimal to) {
        System.out.println("FILTER_BY_PRICE cars");
        return carsService.getAllCars().stream()
                .filter(car -> isInRange(car.getDayPrice(), from, to))
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> filterCarsByYearOfProduction(int yearOfProduction) {
        return Optional.of(yearOfProduction)
                .map(m -> carsService.getAllCars().stream()
                        .filter(car -> car.getYearOfProduction() == yearOfProduction))
                .orElse(carsService.getAllCars().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> filterCarsByMark(String mark) {
        return Optional.of(mark)
                .map (getAllCarsByMark (mark))
                .orElse(carsService.getAllCars());
    }

    @Override
    public User findUserByLogin(String login) throws UserException{
        return Optional.of(login)
                .map(this::getUserByLogin)
                .orElseThrow(() -> new UserException("User not found!"));
    }

    private User getUserByLogin(String login) throws UserException {
        return usersService.getAllUsers().stream()
                .filter(user -> user.getUserLogin().equals(login))
                .findFirst().orElseThrow(() -> new UserException("Not found User with given login"));
    }

    @Override
    public User findUserByEmail(String email) throws UserException{
        return Optional.of(email)
                .map(this::getUserByEmail)
                .orElseThrow(() -> new UserException("Not found User with given E-mail"));
    }

    private User getUserByEmail(String email) throws UserException {
        return usersService.getAllUsers().stream()
                .filter(user -> user.getUserEMail().equals(email))
                .findFirst().orElseThrow(() -> new UserException("Not found User with given E-mail"));
    }

    private void changeCarStatusByUser(String userId, String carId, CarStatus carStatus) throws Exception {
        User user = usersService.getUserById(userId)
                .orElseThrow(() -> new UserException("No client with given ID"));
        if(user.getUserCarId() != null) {
            new CarException("User has already rented car");
        }
        Car carToRent = Optional.of(carsService.getCarById(carId)).orElseThrow(() -> new CarException("No car with given id"));

        if(carToRent.getCarStatus() == CarStatus.AVAILABLE){
            carToRent.setCarStatus(CarStatus.RENTED);
        }

        carsService.updateCar(new CarDto(carToRent.getMark(),carToRent.getModel(),carToRent.getBodyType(),
                carToRent.getYearOfProduction(),carToRent.getColour(),carToRent.getRun(),
                carToRent.getCarStatus(), carToRent.getDayPrice()), carId);

        if(carStatus.equals(CarStatus.AVAILABLE)){
            user.setUserCarId(carId);
        }else{
            user.setUserCarId("");
        }

        usersService.updateUser(new UserDto(user.getUserLogin(),user.getUserPassword(),user.getUserName()
                ,user.getUserLastName(),user.getUserEMail(),user.getUserAddress(),user.getUserCarId(),user.getRole(),user.getStatus()),user.getUserId());
    }

    private boolean isInRange(BigDecimal price, BigDecimal from, BigDecimal to) {
        return price.compareTo(from)  > 0  && price.compareTo(to) < 0;
            // price is larger than 500 and less than 1000
    }
    private Function<String, List<Car>> getAllCarsByMark(String mark) {
        return m -> carsService.getAllCars().stream()
                .filter(car -> car.getMark().equals(mark))
                .collect(Collectors.toList());
    }
}
