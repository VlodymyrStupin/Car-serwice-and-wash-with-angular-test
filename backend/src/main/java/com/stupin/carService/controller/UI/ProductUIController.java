package com.stupin.carService.controller.UI;

import com.stupin.carService.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("")
public class ProductUIController {

    private final ProductService productService;

    @Autowired
    public ProductUIController(ProductService productService) {
        this.productService = productService;
    }
}
