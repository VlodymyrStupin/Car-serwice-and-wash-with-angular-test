package com.stupin.carService.controller.UI;

import com.stupin.carService.domain.dto.User;
import com.stupin.carService.repository.UserRepository;
import com.stupin.carService.service.impl.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(LoginUIController.class)
public class LoginUIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Test
    public void testGetLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/login_page"));
    }

    @Test
    public void testGetLoginErrorPage() throws Exception {
        mockMvc.perform(get("/login-error"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/login_page"))
                .andExpect(model().attributeExists("loginError"))
                .andExpect(model().attribute("loginError", true));
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("logout"))
                .andExpect(model().attribute("logout", true))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", new User()));
        // check that a new user is created
    }

}
