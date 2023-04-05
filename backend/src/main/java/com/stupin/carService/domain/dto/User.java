package com.stupin.carService.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    @Size(min = 2, max = 16, message = "Name must be between 2 and 16 characters")
    private String name;
    private String surname;
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 2, max = 25, message = "Password be between 2 and 25 characters")
    private String password;
    private String phoneNumber;
    private List<Car> cars;

    public User(Long id, String name, String surname, String email,
                String password, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
