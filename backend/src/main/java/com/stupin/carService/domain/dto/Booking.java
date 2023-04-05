package com.stupin.carService.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private Integer id;
    private String email;
    @FutureOrPresent(message = "Date must be present or future")
    private String date;
    private String service;
    private String description;
    private User user;
}
