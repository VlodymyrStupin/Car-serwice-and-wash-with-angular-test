package com.stupin.carService.controller.REST;

import com.stupin.carService.domain.dto.AuthRequest;
import com.stupin.carService.service.impl.LoginServiceImpl;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.sun.activation.registries.LogSupport.log;

@Log4j2
@RestController
@AllArgsConstructor
public class LoginController {

    private final LoginServiceImpl loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        log("Try logg in with authenticated request " + authRequest);
        return loginService.login(authRequest);
    }
}
