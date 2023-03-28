package com.stupin.carService.controller.UI

import com.stupin.carService.domain.dto.Booking
import com.stupin.carService.domain.dto.Product
import com.stupin.carService.domain.dto.User
import com.stupin.carService.service.impl.BookingService
import com.stupin.carService.service.impl.EmailSenderService
import com.stupin.carService.service.impl.ProductService
import com.stupin.carService.service.impl.UserService
import org.junit.Test
import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.web.servlet.view.RedirectView
import static org.mockito.Mockito.*

class BookingUIControllerTest {
    @Mock
    EmailSenderService emailSenderService
    @Mock
    ProductService productService
    @Mock
    UserService userService
    @Mock
    BookingService bookingService
    @InjectMocks
    BookingUIController bookingUIController

    @Before
    void setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    void testViewUserAppointmentPage() {
        when(productService.getAll()).thenReturn([new Product("id", 0, "productName")])
        when(userService.getById(anyInt())).thenReturn(new User(0, "name", "surname", "email", "password", "phoneNumber", [null]))

        String result = bookingUIController.viewUserAppointmentPage(0, null)
        assert result == "replaceMeWithExpectedResult"
    }

    @Test
    void testCreateUserAppointment() {
        when(userService.getByEmail(anyString())).thenReturn(
                new User(0, "name", "surname", "email", "password", "phoneNumber", [null]))
        when(bookingService.save(any())).thenReturn(
                new Booking(0, "email", "date", "service", "description",
                        new User(0, "name", "surname", "email", "password", "phoneNumber", [null])))

        RedirectView result = bookingUIController.createUserAppointment(
                new Booking(0, "email", "date", "service", "description",
                        new User(0, "name", "surname", "email", "password", "phoneNumber", [null])), "email", "service", "date", "description")
        assert result == null
    }
}