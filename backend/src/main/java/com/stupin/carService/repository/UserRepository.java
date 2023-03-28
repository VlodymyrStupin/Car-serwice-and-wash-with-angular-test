package com.stupin.carService.repository;

import com.stupin.carService.domain.dao.UserDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDao, Integer> {

    @Query("SELECT u FROM UserDao u WHERE u.email = ?1")
    UserDao findByEmail(String email);

}
