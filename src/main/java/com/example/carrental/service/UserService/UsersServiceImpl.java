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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public void updateUser(UserDto userDto, String id) throws Exception {
        System.out.println("UPDATING client by ID");
        User userToUpdate = getUserById(id)
                .map(user -> new User(user.getUserId(), userDto.getUserLogin(), userDto.getUserPassword(),
                        userDto.getUserName(), userDto.getUserLastName(), userDto.getUserEMail(), userDto.getUserAddress(),
                        null, userDto.getRole(), userDto.getStatus()))
                .orElseThrow(() -> new UserException("No client with given id"));
        userRepository.save(userToUpdate);
    }

    @Override
    public List<User> getAllUsers() {
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
    public User findUserByUserLogin(String login) throws UserException{
        return Optional.of(login)
                .map(this::getUserByLogin)
                .orElseThrow(() -> new UserException("User not found!"));
    }

    @Override
    public User findUserByUserEmail(String email) throws UserException{
        return Optional.of(email)
                .map(this::getUserByEmail)
                .orElseThrow(() -> new UserException("Not found User with given E-mail"));
    }

    private User getUserByLogin(String login) throws UserException {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserLogin().equals(login))
                .findFirst().orElseThrow(() -> new UserException("Not found User with given login"));
    }

    private User getUserByEmail(String email) throws UserException {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserEMail().equals(email))
                .findFirst().orElseThrow(() -> new UserException("Not found User with given E-mail"));
    }

}
