package com.stupin.carService.controller.UI;

import com.stupin.carService.converter.UserConverter;
import com.stupin.carService.domain.dto.User;
import com.stupin.carService.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterUIController {
    private final Logger log = LogManager.getLogger(RegisterUIController.class);
    private final UserRepository userRepository;


    @Autowired
    public RegisterUIController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String getRegistrationFormPage(Model model) {
        model.addAttribute("user", new User());
        return "register/register_page";
    }
    @PostMapping("/process-register")
    public String processRegister(@ModelAttribute User user,
                                  @RequestParam String name,
                                  @RequestParam String surname,
                                  @RequestParam String email,
                                  @RequestParam String phoneNumber,
                                  @RequestParam String password,
                                  BindingResult bindingResult
    ) {
        if (userRepository.findByEmail(email) != null) {
            return "redirect:/register-error";
        }
        if (bindingResult.hasErrors()) {
            return "/register";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setName(name);
        user.setSurname(surname);
        userRepository.save(UserConverter.toDao(user));
        return "redirect:/register-success";
    }
    @GetMapping("/register-error")
    public String getRegisterErrorPage(Model model) {
        model.addAttribute("processRegister", true);
        return "register/register_page";
    }
    @GetMapping("/register-success")
    public String getSuccessRegisterPage(Model model) {
        model.addAttribute("processRegister", true);
        return "login/login_page";
    }
}
