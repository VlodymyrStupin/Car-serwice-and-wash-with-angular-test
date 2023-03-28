package com.stupin.carService.controller.UI;

import com.stupin.carService.domain.dto.User;
import com.stupin.carService.repository.UserRepository;
import com.stupin.carService.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginUIController {
    private final Logger log = LogManager.getLogger(RegisterUIController.class);
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public LoginUIController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @GetMapping("/login")
    public String geyLoginPage() {
        return "login/login_page";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login/login_page";
    }
    @GetMapping("/logout")
    public String logoutPage (Model model, HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user= new User();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.addAttribute("logout", true);
        model.addAttribute("user", user);
        return "index";
    }
}
