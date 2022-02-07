package com.project.soruCevap.controllers;

import com.project.soruCevap.entities.User;
import com.project.soruCevap.services.UserService;
import com.project.soruCevap.shared.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User newUser){
        String firstName = newUser.getFirstName();
        String lastName = newUser.getLastName();
        String email = newUser.getEmail();
        String password = newUser.getPassword();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()){

        }
        userService.createUser(newUser);
        return ResponseEntity.ok(new GenericResponse("User created success!"));
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable String userId){
        return userService.getOneUserById(userId);
    }

    @PutMapping("/update/{userId}")
    public User updateOneUser(@PathVariable String userId, @RequestBody User updateUser){
        return userService.updateOneUser(userId, updateUser);
    }

    @DeleteMapping("/delete/{userId}")
    public Boolean deteleOneUser(@PathVariable String userId){
        return userService.deleteUserById(userId);
    }

}
