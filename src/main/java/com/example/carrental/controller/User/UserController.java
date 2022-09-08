package com.example.carrental.controller.User;

import com.example.carrental.controller.OwnExceptionHandler;
import com.example.carrental.domain.User.User;
import com.example.carrental.domainDto.UserDto.UserDto;
import com.example.carrental.service.UserService.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public User createUser(@RequestBody @Valid UserDto userDto){
        return usersService.createUser(userDto);
    }

    @PutMapping(path = "/{id}")
    public void updateUser(@RequestBody UserDto userDto, @PathVariable String id) throws Exception{
        usersService.updateUser(userDto, id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Collection<User> getAllUser(){
        return usersService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        if(id.isEmpty()){
            return new OwnExceptionHandler().getResponseHttpNotFound();
        }
        return new ResponseEntity<>(usersService.getUserById(id).get(),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable String id) throws Exception{
        usersService.deleteUserById(id);
    }

    @GetMapping(path="/userEmail/{email}")
    @ResponseStatus(HttpStatus.FOUND)
    public User findUserByEmail(@PathVariable String email){
        return usersService.findUserByUserEmail(email);
    }

    @GetMapping(path="/userLogin/{login}")
    @ResponseStatus(HttpStatus.FOUND)
    public User findUserByLogin(@PathVariable String login){
        return usersService.findUserByUserLogin(login);
    }

}
