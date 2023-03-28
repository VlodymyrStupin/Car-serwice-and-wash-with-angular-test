package com.stupin.carService.controller.REST;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WelcomeEndpoint {

    @GetMapping("/rest/hello")
    public List<String> hello(){
        return  List.of("hello", "how are you");

    }
}
