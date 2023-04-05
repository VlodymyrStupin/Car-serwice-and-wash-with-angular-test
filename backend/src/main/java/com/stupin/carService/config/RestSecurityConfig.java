package com.stupin.carService.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;



@Configuration
public class RestSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    private final CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    public RestSecurityConfig(JwtTokenFilter jwtTokenFilter,
                              CustomAuthenticationSuccessHandler successHandler) {
        this.jwtTokenFilter = jwtTokenFilter;
        this.successHandler = successHandler;
    }

    // Add 2 users at start
//    @EventListener(ApplicationReadyEvent.class)
//    public void saveUser() {
//        UserImplUserDetails user1 = new UserImplUserDetails("test1@gmail.com",
//                getBcryptPasswordEncoder().encode("admin123"), "ROLE_USER");
//        userRepo.save(user1);
//        UserImplUserDetails user2 = new UserImplUserDetails("test2@gmail.com",
//                getBcryptPasswordEncoder().encode("admin123"), "ROLE_ADMIN");
//        userRepo.save(user2);
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> userService.getByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User with email not found: " + username));
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authorizationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("users/**").hasRole("USER")
                .antMatchers("admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error")
                .permitAll()
                .successHandler(successHandler)
                .and()
                .logout().permitAll()
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
