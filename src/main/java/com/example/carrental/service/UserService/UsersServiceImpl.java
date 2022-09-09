package com.example.carrental.service.UserService;

import com.example.carrental.domain.User.User;
import com.example.carrental.domain.User.UserException;
import com.example.carrental.domainDto.UserDto.UserDto;
import com.example.carrental.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserDto userDto) {

        User user = User.builder()
                .userLogin(userDto.getUserLogin())
                .userPassword(userDto.getUserPassword())
                .userName(userDto.getUserName())
                .userLastName(userDto.getUserLastName())
                .userEMail(userDto.getUserEMail())
                .userAddress(userDto.getUserAddress())
                .userCarId(userDto.getUserCarId())
                .role(userDto.getRole())
                .status(userDto.getStatus())
                .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public void updateUser(UserDto userDto, Long id) throws Exception {
        User userToUpdate = getUserById(id);

        userRepository.save(new User(userToUpdate.getId(), userDto.getUserLogin(), userDto.getUserPassword(),
                userDto.getUserName(), userDto.getUserLastName(), userDto.getUserEMail(), userDto.getUserAddress(),
                userDto.getUserCarId(), userDto.getRole(), userDto.getStatus(),userDto.getRentalOfficeList()));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id)   {
        Long userFindById = Optional.of(id).orElseThrow(() -> new UserException("No Client found in DB"));
        return userRepository.findById(userFindById);
    }

    @Override
    public void deleteUserById(Long id) {
        Optional.of(getUserById(id)).orElseThrow(() -> new UserException("No client with given ID"));
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
