package com.stupin.carService.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Car {
    String id;
    private String brand;
    private String model;
    private int odometer;
    private int productionYear;
    private User user;

    public Car(String id, String brand, String model, int odometer, int productionYear, User user) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.odometer = odometer;
        this.productionYear = productionYear;
        this.user = user;
    }

    public Car(Integer carId, String brand, String model, int odometer, int productionYear) {

    }
}
