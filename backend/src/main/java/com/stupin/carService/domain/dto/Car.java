package com.stupin.carService.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class Car {
    String id;
    private String brand;
    private String model;
    @Positive (message = "Odometer must be positive")
    private int odometer;
    @Min(value = 1980, message = "Production year should not be less than 1980")
    @Max(value = 2023, message = "Production year should not be greater than 2023")
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
