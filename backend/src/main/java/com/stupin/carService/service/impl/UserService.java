package com.stupin.carService.service.impl;


import com.stupin.carService.converter.UserConverter;
import com.stupin.carService.domain.dao.UserDao;
import com.stupin.carService.domain.dto.User;
import com.stupin.carService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(it -> users.add(new User(it.getId(), it.getName(), it.getSurname(),
                it.getEmail(), it.getPassword(), it.getPhoneNumber())));
        return users;
    }
    public User getById(Long id) {
        return UserConverter.toDto(userRepository.findById(id).orElseThrow(()
                -> new UsernameNotFoundException("User with id not found: " + id)));
    }

    //Add user not found exception for this method(create new exception)
    public User getByEmail(String email) {
        return UserConverter.toDto(userRepository.findByEmail(email));
    }
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
    public User save(User user) {
        UserDao dao;
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            dao = userRepository.findById(user.getId()).get();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            dao.setPassword(encodedPassword);
            dao.setName(user.getName());
            dao.setSurname(user.getSurname());
            dao.setEmail(user.getEmail());
            dao.setPhoneNumber(user.getPhoneNumber());
        } else {
            dao = UserConverter.toDao(user);
        }
        return UserConverter.toDto(userRepository.save(dao));
    }
}
