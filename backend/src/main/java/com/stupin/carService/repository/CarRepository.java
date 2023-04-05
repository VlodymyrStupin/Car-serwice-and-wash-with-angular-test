package com.stupin.carService.repository;

import com.stupin.carService.domain.dao.CarDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CarRepository extends JpaRepository<CarDao, String> {
    long deleteByIdIgnoreCase(String id);
    @Query("SELECT c FROM CarDao c WHERE c.id = ?1")
    CarDao findByVinCode(String id);
    @Query("SELECT c FROM CarDao c WHERE c.userDao.id = ?1")
    List<CarDao> findByUserId(Long id);
    @Query("DELETE FROM CarDao c WHERE c.id = ?1")
    void deleteByVin(String vin);
}
