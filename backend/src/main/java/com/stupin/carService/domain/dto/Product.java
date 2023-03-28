package com.stupin.carService.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
    private String id;
    private int price;
    private String productName;

    public Product(String id, int price, String productName) {
        this.id = id;
        this.price = price;
        this.productName = productName;
    }
}
