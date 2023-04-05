package com.stupin.carService.domain.dao;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class UserDao implements UserDetails {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @Column(unique = true, length = 45)
    private String email;
    @Column(nullable = false, length = 64)
    private String password;
    @Column
    private String role = "USER";
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 20)
    private String surname;
    @Column(unique = true, length = 12)
    private String phoneNumber;
    @Column
    private Integer enabled = 1;
    @OneToMany(mappedBy = "userDao", cascade = CascadeType.ALL)
    private List<CarDao> cars;
    @OneToMany(orphanRemoval = true)
    private List<BookingDao> bookings = new ArrayList<>();
    public UserDao(Long id, String name, String surname, String email,
                   String password, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserImplUserDetails{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", enabled=" + enabled +
                ", cars=" + cars +
                ", bookings=" + bookings +
                '}';
    }
}
