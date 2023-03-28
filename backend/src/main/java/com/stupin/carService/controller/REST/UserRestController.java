package com.stupin.carService.controller.REST;


import com.stupin.carService.domain.dto.User;
import com.stupin.carService.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {
    // standard constructors

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping("/add-user")
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }
}