package com.stupin.carService.converter;

import com.stupin.carService.domain.dao.UserDao;
import com.stupin.carService.domain.dto.User;

public class UserConverter {
    public static User toDto(UserDao userDao) {
        return new User(
                userDao.getId(),
                userDao.getName(),
                userDao.getSurname(),
                userDao.getEmail(),
                userDao.getPassword(),
                userDao.getPhoneNumber());
    }

    public static UserDao toDao(User user) {
        return new UserDao(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber());
    }
}
