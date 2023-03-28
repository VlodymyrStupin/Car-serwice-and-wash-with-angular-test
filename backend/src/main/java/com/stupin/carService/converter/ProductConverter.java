package com.stupin.carService.converter;

import com.stupin.carService.domain.dao.ProductDao;
import com.stupin.carService.domain.dto.Product;

public class ProductConverter {
    public static Product toDto(ProductDao productDao) {
        return new Product(
                productDao.getId(),
                productDao.getPrice(),
                productDao.getProductName());
    }

    public static ProductDao toDao(Product product) {
        return new ProductDao(
                product.getId(),
                product.getPrice(),
                product.getProductName());
    }
}
