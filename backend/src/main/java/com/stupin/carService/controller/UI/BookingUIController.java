package com.stupin.carService.controller.UI;

import com.stupin.carService.domain.dto.Booking;
import com.stupin.carService.domain.dto.Email;
import com.stupin.carService.domain.dto.User;
import com.stupin.carService.service.impl.BookingService;
import com.stupin.carService.service.impl.EmailSenderService;
import com.stupin.carService.service.impl.ProductService;
import com.stupin.carService.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BookingUIController {
    private final EmailSenderService emailSenderService;
    private final ProductService productService;
    private final UserService userService;
    private final BookingService bookingService;

    @Autowired
    public BookingUIController(EmailSenderService emailSenderService, ProductService productService, UserService userService, BookingService bookingService) {
        this.emailSenderService = emailSenderService;
        this.productService = productService;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("ui/users/{id}/booking")
    public String viewUserAppointmentPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("products", productService.getAll());
        model.addAttribute("booking", new Booking());
        return "booking/user_booking_page";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("ui/users/{id}/booking")
    public RedirectView createUserAppointment(@ModelAttribute Booking booking,
                                              @RequestParam String email,
                                              @RequestParam String service,
                                              @RequestParam String date,
                                              @RequestParam String description) {
        User user = userService.getByEmail(email);
        booking.setEmail(email);
        booking.setService(service);
        booking.setDate(date);
        booking.setDescription(description);
        booking.setUser(user);
        Email emailObject = new Email();
        emailObject.setTo("rav.elbud@gmail.com");
        emailObject.setSubject("Confirmation reservation date for service " + "service");
        emailObject.setText("Dear user your car have been successfully booked for "
                + "service" + ". Appointment date is " + date);

        emailSenderService.sendConfirmationBookingMail(emailObject);
        bookingService.save(booking);
        return new RedirectView("");
    }
}
