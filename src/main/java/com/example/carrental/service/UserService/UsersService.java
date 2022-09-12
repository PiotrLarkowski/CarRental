package com.example.carrental.service.UserService;

import com.example.carrental.domain.User.User;
import com.example.carrental.domain.User.UserException;
import com.example.carrental.domainDto.UserDto.UserDto;

import java.util.List;

public interface UsersService {

    User createUser(UserDto userDto);

    void updateUser(UserDto userDto, Long id) throws Exception;

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUserById(Long id) throws Exception;

    User findUserByUserEmail(String email) throws UserException;

    User findUserByUserLogin(String login) throws UserException;

}
