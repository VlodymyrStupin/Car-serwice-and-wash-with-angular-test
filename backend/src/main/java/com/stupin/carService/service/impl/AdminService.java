package com.stupin.carService.service.impl;

import java.util.List;

import com.stupin.carService.domain.dao.AdminDao;
import com.stupin.carService.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public void editUser(){

    }

    public void createAssortment (){

    }

    public void sendConfirmationEmail(){

    }


    public List<AdminDao> findAll() {
        return adminRepository.findAll();
    }


    public void save(AdminDao adminDao) {

        adminRepository.save(adminDao);
    }
}
