package com.stupin.carService.repository;

import java.util.List;

import com.stupin.carService.domain.dao.AdminDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminDao, Long> {

    AdminDao findByEmail(String user);

    List<AdminDao> findByRole(String user);
}