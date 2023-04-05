package com.stupin.carService.config;

import com.stupin.carService.domain.dto.User;
import com.stupin.carService.service.impl.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String email = authentication.getName();
        User user = userService.getByEmail(email);
        Long id = user.getId();
        if (roles.contains("ADMIN")) {
            httpServletResponse.sendRedirect("admin/manage");
        }
        if (roles.contains("USER")) {
            httpServletResponse.sendRedirect("users/" + id);
        }
    }
}
