package com.stupin.carService.domain.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cars")
@NoArgsConstructor
public class CarDao {
    @Id
    @Column(name = "vin")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private int odometer;
    @Column(name = "production_year")
    private int productionYear;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDao userDao;

    public CarDao(String id, String brand, String model, int odometer, int productionYear, UserDao userDao) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.odometer = odometer;
        this.productionYear = productionYear;
        this.userDao = userDao;
    }
}
