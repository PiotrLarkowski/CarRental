package com.example.carrental.service.UserService;

import com.example.carrental.domain.User.User;
import com.example.carrental.domain.User.UserException;
import com.example.carrental.domainDto.UserDto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    User createUser(UserDto userDto);

    void updateUser(UserDto userDto, String id) throws Exception;

    List<User> getAllUsers();

    Optional<User> getUserById(String id);

    void deleteUserById(String id) throws Exception;

    User findUserByUserEmail(String email) throws UserException;

    User findUserByUserLogin(String login) throws UserException;

}
