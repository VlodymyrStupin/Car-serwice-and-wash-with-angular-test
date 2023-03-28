package com.stupin.carService.repository;

import com.stupin.carService.domain.dao.ProductDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDao, String> {

    ProductDao getById(String id);
}
