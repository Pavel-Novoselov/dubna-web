package ru.geekbrains.comand.geetterbackend.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.comand.geetterbackend.entities.dto.UserDto;
import ru.geekbrains.comand.geetterbackend.exceptions.IncorrectRequestException;
import ru.geekbrains.comand.geetterbackend.services.interfaces.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Api(value = "Users")
public class UserRestController {

    private final UserService userService;

    @ApiOperation(value = "Get List of all users")
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @ApiOperation(value = "Create user")
    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto user) {
    	if (user.getId() != null)
    		throw new IncorrectRequestException("User already exists");
        return userService.saveOrUpdate(user);

    }

    @ApiOperation(value = "Update user")
    @PutMapping
    public UserDto updateUser(@RequestBody @Valid UserDto user) {
    	if (user.getId() == null)
    		throw new IncorrectRequestException("User does not exist");
        return userService.saveOrUpdate(user);
    }

    @ApiOperation(value = "Get user by id")
    @GetMapping(path = "/{id}/id")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @ApiOperation(value = "Get user by username")
    @GetMapping(path = "/{login}/login")
    public UserDto getUserByUsername(@PathVariable("login") String username) {
        return userService.findOneByUsername(username);
    }

    @ApiOperation(value = "Get user by email")
    @GetMapping(path = "/{email}/email")
    public UserDto getUserByEmail(@PathVariable("email") String email) {
        return userService.findOneByEmail(email);
    }

    @ApiOperation(value = "Get user by phone")
    @GetMapping(path = "/{phone}/phone")
    public UserDto getUserByPhone(@PathVariable("phone") String phone) {
        return userService.findOneByPhone(phone);
    }

    @ApiOperation(value = "Delete user by id")
    @DeleteMapping(path = "/{id}/id")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
    
    @PutMapping(path = "/{id}/subscribe")
    public void subscribe(@PathVariable("id") Long userId, @RequestParam("target") Long targetUserId) {
		userService.subscribe(userId, targetUserId);
    }
    
    @PutMapping(path = "/{id}/unsubscribe")
    public void unsubscribe(@PathVariable("id") Long userId, @RequestParam("target") Long targetUserId) {
		userService.unsubscribe(userId, targetUserId);
    }
    
    @GetMapping(path = "/{id}/subscriptions")
    public List<UserDto> getAllTheUserIsSubscribedTo(@PathVariable("id") Long userId) {
    	return userService.getAllTheUserIsSubscribedTo(userId);
    }

}
