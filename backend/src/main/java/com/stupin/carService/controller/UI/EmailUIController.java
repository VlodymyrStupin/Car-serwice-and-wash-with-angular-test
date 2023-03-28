package com.stupin.carService.controller.UI;

import com.stupin.carService.service.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailUIController {
    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailUIController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @GetMapping("/send")
    public String sendEmail() {
//create page with field to send email
//        Email email= new Email()
//        emailSenderService.sendSimpleMail();
        return "home";
    }

}
