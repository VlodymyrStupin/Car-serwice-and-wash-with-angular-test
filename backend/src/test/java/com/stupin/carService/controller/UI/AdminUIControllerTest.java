package com.stupin.carService.controller.UI;

import com.stupin.carService.domain.dto.Product;
import com.stupin.carService.domain.dto.User;
import com.stupin.carService.service.impl.ProductService;
import com.stupin.carService.service.impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static org.mockito.Mockito.*;

class AdminUIControllerTest {
    @Mock
    Logger log;
    @Mock
    UserService userService;
    @Mock
    ProductService productService;
    @InjectMocks
    AdminUIController adminUIController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewAdminPage() {
        String result = adminUIController.viewAdminPage();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testViewAdminManagePage() {
        when(userService.getAll()).thenReturn(List.of(new User(Integer.valueOf(0), "name", "surname", "email", "password", "phoneNumber", List.of(null))));

        String result = adminUIController.viewAdminManagePage(null);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetEditUserPage() {
        when(userService.getAll()).thenReturn(List.of(new User(Integer.valueOf(0), "name", "surname", "email", "password", "phoneNumber", List.of(null))));
        when(userService.getById(anyInt())).thenReturn(new User(Integer.valueOf(0), "name", "surname", "email", "password", "phoneNumber", List.of(null)));

        String result = adminUIController.getEditUserPage(Integer.valueOf(0), null);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testEditUser() {
        when(userService.save(any())).thenReturn(new User(Integer.valueOf(0), "name", "surname", "email", "password", "phoneNumber", List.of(null)));

        String result = adminUIController.editUser(new User(Integer.valueOf(0), "name", "surname", "email", "password", "phoneNumber", List.of(null)), "name", "surname", "email", "phoneNumber", "password");
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetProductAddPage() {
        when(productService.getAll()).thenReturn(List.of(new Product("id", 0, "productName")));

        String result = adminUIController.getProductAddPage(null);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetEditProductPage() {
        when(productService.getById(anyString())).thenReturn(
                new Product("id", 0, "productName"));
        when(productService.getAll()).thenReturn(List.of(
                new Product("id", 0, "productName")));

        String result = adminUIController.getEditProductPage("id", null);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testEditProducts() {
        when(productService.getById(anyString())).thenReturn(
                new Product("id", 0, "productName"));
        when(productService.save(any())).thenReturn(
                new Product("id", 0, "productName"));

        RedirectView result = adminUIController.editProducts("id", 0, "productName");
        Assertions.assertEquals(null, result);
    }

    @Test
    void testAddProduct() {
        when(productService.save(any())).thenReturn(
                new Product("id", 0, "productName"));

        RedirectView result = adminUIController.addProduct(
                new Product("id", 0, "productName"), 0, "productName");
        Assertions.assertEquals(null, result);
    }

    @Test
    void testRemoveProduct() {
        RedirectView result = adminUIController.removeProduct("id", null);
        Assertions.assertEquals(null, result);
    }
}