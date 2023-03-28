package com.stupin.carService.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private Integer id;

    private String email;
    private String date;
    private String service;
    private String description;
    private User user;
}
