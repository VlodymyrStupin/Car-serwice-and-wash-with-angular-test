package com.stupin.carService.service;

import com.stupin.carService.domain.dto.AuthRequest;
import com.stupin.carService.domain.dto.Token;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<?> login(AuthRequest authRequest);
}
