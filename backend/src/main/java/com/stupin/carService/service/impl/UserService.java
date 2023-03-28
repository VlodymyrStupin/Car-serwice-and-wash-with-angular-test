package com.stupin.carService.service.impl;


import com.stupin.carService.converter.UserConverter;
import com.stupin.carService.domain.dao.UserDao;
import com.stupin.carService.domain.dto.User;
import com.stupin.carService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(it -> users.add(new User(it.getId(), it.getName(), it.getSurname(),
                it.getEmail(), it.getPassword(), it.getPhoneNumber())));
        return users;
    }
    public User getById(Integer id) {
        return UserConverter.toDto(userRepository.findById(id).orElseThrow(() -> new RuntimeException("")));
    }
    public User getByEmail(String email) {
        return UserConverter.toDto(userRepository.findByEmail(email));
    }
    public void delete(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
    public User save(User user) {
        UserDao dao;
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            dao = userRepository.findById(user.getId()).get();
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
