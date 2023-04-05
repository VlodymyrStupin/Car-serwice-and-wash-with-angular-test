package com.stupin.carService.controller.UI;

import com.stupin.carService.domain.dto.User;
import com.stupin.carService.repository.UserRepository;
import com.stupin.carService.service.impl.AdminService;
import com.stupin.carService.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("ui/users/")
public class UserUIController {
    private final Logger log = LogManager.getLogger(UserUIController.class);
    private final UserRepository userRepository;
    private final UserService userService;

    private AdminService adminService;

    @Autowired
    public UserUIController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/")
    public String viewUserPage() {
        return "user/user_page";
    }


    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("{id}/profile")
    public String viewUserProfilePage(@PathVariable Long id, Model model) {
        model.addAttribute(model.addAttribute("user", userService.getById(id)));
        return "user/user_profile_page";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/{id}/manage-profile")
    public String manageUserProfile(@ModelAttribute User user,
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
        return "redirect:/register-success";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{id}")
    public String viewUserPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user/user_page";
    }
}

