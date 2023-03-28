package com.stupin.carService.controller.REST;

import com.stupin.carService.domain.dto.AuthRequest;
import com.stupin.carService.service.impl.LoginServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserImplUserDetailsRestController {
    private final LoginServiceImpl loginService;

    @PostMapping("/rest/login")
    public ResponseEntity<?> getJwt(@RequestBody AuthRequest authRequest) {
        return loginService.login(authRequest);
    }
}
