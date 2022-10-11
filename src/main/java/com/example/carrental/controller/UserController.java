package com.example.carrental.controller;

import com.example.carrental.domain.User.CarRentalUser;
import com.example.carrental.domainDto.UserDto.UserDto;
import com.example.carrental.service.UserService.UsersService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarRentalUser createUser(@RequestBody @Valid UserDto userDto){
        return usersService.createUser(userDto);
    }

    @PostMapping(path = "/log/in")
    public CarRentalUser sendUser(@RequestBody @Valid @NotNull UserDto userDto){
        return usersService.findUserByUserLogin(userDto.getUserLogin());
    }
    @Secured({"ROLE_MODERATOR","ROLE_ADMIN"})
    @PutMapping(path = "/{id}")
    public void updateUser(@RequestBody UserDto userDto, @PathVariable Long id) throws Exception{
        usersService.updateUser(userDto, id);
    }
    @Secured({"ROLE_MODERATOR","ROLE_ADMIN"})
    @GetMapping
    public Collection<CarRentalUser> getAllUser(){
        return usersService.getAllUsers();
    }
    @Secured({"ROLE_MODERATOR","ROLE_ADMIN"})
    @GetMapping(path = "{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(usersService.getUserById(id),HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable Long id) throws Exception{
        usersService.deleteUserById(id);
    }
    @Secured({"ROLE_MODERATOR","ROLE_ADMIN"})
    @GetMapping(path="/userEmail/{email}")
    public CarRentalUser findUserByEmail(@PathVariable String email){
        return usersService.findUserByUserEmail(email);
    }
    @Secured({"ROLE_MODERATOR","ROLE_ADMIN"})
    @GetMapping(path="/userLogin/{login}")
    public CarRentalUser findUserByLogin(@PathVariable String login){
        return usersService.findUserByUserLogin(login);
    }

}
