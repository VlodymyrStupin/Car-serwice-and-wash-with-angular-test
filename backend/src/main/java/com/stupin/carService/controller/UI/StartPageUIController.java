package com.stupin.carService.controller.UI;

import com.stupin.carService.domain.dto.User;
import com.stupin.carService.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPageUIController {
    private final UserService userService;

    @Autowired
    public StartPageUIController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getStartPage(Authentication authentication, Model model) {
        User user = new User();
        if (authentication != null) {
            String email = authentication.getName();
            user = userService.getByEmail(email);
        }
        model.addAttribute("user", user);
        return "index";
    }
}
