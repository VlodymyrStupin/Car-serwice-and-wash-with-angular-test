package com.stupin.carService.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.stupin.carService.domain.dao.UserDao;
import com.stupin.carService.domain.dto.AuthRequest;
import com.stupin.carService.domain.dto.AuthResponse;

import com.stupin.carService.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    @Value("${secret.key}")
    private String KEY;

    private final AuthenticationManager authorizationManager;

    @Override
    public ResponseEntity<?> login(AuthRequest authRequest) {
        try {
            Authentication authenticate = authorizationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            UserDao user = (UserDao) authenticate.getPrincipal();

            Algorithm algorithm = Algorithm.HMAC256(KEY);
            String token = JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuer("Volodymyr")
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .withClaim("roles", user.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .sign(algorithm);

            AuthResponse authResponse = new AuthResponse(user.getUsername(), token);
            return ResponseEntity.ok(authResponse);
        } catch (UsernameNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
