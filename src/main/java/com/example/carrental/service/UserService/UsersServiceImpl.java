package com.example.carrental.service.UserService;

import com.example.carrental.domain.Car.Car;
import com.example.carrental.domain.Car.CarException;
import com.example.carrental.domain.Car.CarStatus;
import com.example.carrental.domain.User.User;
import com.example.carrental.domain.User.UserException;
import com.example.carrental.domainDto.CarDto.CarDto;
import com.example.carrental.domainDto.UserDto.UserDto;
import com.example.carrental.repository.UserRepository;
import com.example.carrental.service.CarService.CarsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    private final CarsService carsService;

    public UsersServiceImpl(UserRepository userRepository, CarsService carsService) {
        this.userRepository = userRepository;
        this.carsService = carsService;
    }

    @Override
    public User createUser(UserDto userDto) {
        System.out.println("Creating a new Client");
        User user = new User(UUID.randomUUID().toString(), userDto.getUserLogin(), userDto.getUserPassword(),
                userDto.getUserName(), userDto.getUserLastName(), userDto.getUserEMail(), userDto.getUserAddress(),
                null, userDto.getRole(), userDto.getStatus());
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(UserDto userDto, String id) throws Exception {
        System.out.println("UPDATING client by ID");
        User userToUpdate = getUserById(id)
                .map(user -> new User(user.getUserId(), userDto.getUserLogin(), userDto.getUserPassword(),
                        userDto.getUserName(), userDto.getUserLastName(), userDto.getUserEMail(), userDto.getUserAddress(),
                        null, userDto.getRole(), userDto.getStatus()))
                .orElseThrow(() -> new UserException("No client with given id"));
        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String id)   {
        System.out.println("Get Client by Id");
        Optional<String> optionalID = Optional.of(id);
        if(optionalID.isEmpty()){
            new UserException("No Client found in DB");
        }
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(String id) throws Exception {
        System.out.println("DELETING client by ID");
        getUserById(id).orElseThrow(() -> new UserException("No client with given ID"));
        userRepository.deleteById(id);
    }

    @Override
    public void rentCar(String userId, String carId) throws Exception {

    }

    @Override
    public void returnCar(String userId, String carId) throws Exception {

    }
//
//    @Override
//    public void rentCar(String userId, String carId) throws Exception{
//        changeCarStatusByUser(userId, carId, CarStatus.RENTED);
//    }
//
//    @Override
//    public void returnCar(String userId, String carId) throws Exception {
//        changeCarStatusByUser(userId, carId, CarStatus.AVAILABLE);
//    }

//    private Car changeCarStatusByUser(String userId, String carId, CarStatus carStatus) throws Exception {
//        User user = getUserById(userId)
//                .orElseThrow(() -> new UserException("No client with given ID"));
//        if(!user.getUserCarId().equals(null)) {
//            new CarException("User has already rented car");
//        }
//        CarDto carToRent = carsService.getCarById(carId)
//                .filter(availableCar -> availableCar.getCarStatus().equals(CarStatus.AVAILABLE))
//                .map(carStream -> new CarDto(carStream.getMark(), carStream.getModel(), carStream.getBodyType(),
//                        carStream.getYearOfProduction(), carStream.getColour(), carStream.getRun(),
//                        carStatus, carStream.getDayPrice()))
//                .orElseThrow(() -> new CarException("No car with given ID or car is not available"));
//        carsService.updateCar(carToRent, carId);
//
//        if(carStatus.equals(CarStatus.AVAILABLE)){
//            user.setUserCarId(carId);
//        }else{
//            user.setUserCarId("");
//        }
//
//        userRepository.save(user);
//        return carsService.getCarById(carId).get();
//    }

}
