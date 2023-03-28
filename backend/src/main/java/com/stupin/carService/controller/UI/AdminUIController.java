package com.stupin.carService.controller.UI;


import com.stupin.carService.domain.dto.Product;
import com.stupin.carService.domain.dto.User;
import com.stupin.carService.service.impl.ProductService;
import com.stupin.carService.service.impl.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("ui/admin")
public class AdminUIController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AdminUIController.class);
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public AdminUIController(UserService userService, ProductService productService) {

        this.userService = userService;
        this.productService = productService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public String viewAdminPage() {
        return "admin/admin_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("manage")
    public String viewAdminManagePage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin/manage_users_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{id}/edit")
    public String getEditUserPage(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("users", userService.getAll());
        return "admin/manage_users_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("edit")
    public String editUser(@ModelAttribute User user,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String email,
                           @RequestParam String phoneNumber,
                           @RequestParam String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(encodedPassword);
        user.setName(name);
        user.setSurname(surname);
        userService.save((user));
        return "admin/manage_users_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("products")
    public String getProductAddPage(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("product", new Product());
        model.addAttribute("products", products);
        return "admin/add_product_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{id}/edit-product")
    public String getEditProductPage(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("products", productService.getAll());
        return "admin/manage_product_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("edit-product")
    public RedirectView editProducts(@PathVariable String id,
                                    @RequestParam int price,
                                    @RequestParam String productName) {
        Product product = productService.getById(id);
        product.setPrice(price);
        product.setProductName(productName);
        productService.save(product);
        return new RedirectView("/ui/admin/products");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("add")
    public RedirectView addProduct(@ModelAttribute Product product,
                                   @RequestParam int price,
                                   @RequestParam String productName) {
        product.setPrice(price);
        product.setProductName(productName);
        productService.save(product);
        return new RedirectView("products");
    }

    @GetMapping("{id}/delete")
    public RedirectView removeProduct(@PathVariable String id,
                                      Model model) {
        productService.delete(id);
        model.addAttribute("id", id);
        return new RedirectView("/ui/admin/products");
    }
}