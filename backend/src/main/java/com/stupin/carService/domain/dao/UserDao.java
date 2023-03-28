package com.stupin.carService.domain.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserDao{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 20)
    private String surname;
    @Column(unique = true, length = 45)
    private String email;
    @Column(nullable = false, length = 64)
    private String password;
    @Column(unique = true, length = 12)
    private String phoneNumber;
    @Column
    private Integer enabled = 1;
    @Column
    private String role = "USER";

    @OneToMany(mappedBy = "userDao", cascade = CascadeType.ALL)
    private List<CarDao> cars = new ArrayList<>();
    @OneToMany(orphanRemoval = true)
    private List<BookingDao> bookings = new ArrayList<>();
    public UserDao(Integer id, String name, String surname, String email,
                   String password, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public List<CarDao> getCarEntities() {
        return cars;
    }

    public void setCarEntities(List<CarDao> carEntities) {
        this.cars = carEntities;
    }
}
