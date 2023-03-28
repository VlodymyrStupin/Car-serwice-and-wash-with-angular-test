package com.stupin.carService.domain.dao;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "appointments")
@NoArgsConstructor
public class BookingDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column
    private Integer id;
    @Column
    private String email;
    @Column
    private String date;
    @Column
    private String service;
    @Column
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserDao userDao;

    public BookingDao(int id, String email, String description, String date, String service, UserDao userDao) {
        this.id = id;
        this.email = email;
        this.date = date;
        this.service = service;
        this.description = description;
        this.userDao = userDao;
    }
}