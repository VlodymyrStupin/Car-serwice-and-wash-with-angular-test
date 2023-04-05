package com.stupin.carService.controller.REST;

import com.stupin.carService.domain.dto.User;
import com.stupin.carService.service.impl.UserService;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sun.activation.registries.LogSupport.log;

@RestController
@AllArgsConstructor
@Log4j2
public class RegisterController {
    private final UserService userService;

    @PostMapping("/register")
    public void registration(@RequestBody User user) {
        userService.save(user);
        log("Saved user with id - "  + user.getId());
    }
}
