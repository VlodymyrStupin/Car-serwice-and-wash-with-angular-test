package com.stupin.carService.domain.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
public class ProductDao {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column
    private int price;
    @Column(name = "product_name")
    private String productName;

    public ProductDao(String id, int price, String productName) {
        this.id = id;
        this.price = price;
        this.productName = productName;
    }
}
