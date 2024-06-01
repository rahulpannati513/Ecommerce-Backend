//package org.rahul.ecommercebackend.Config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.rahul.ecommercebackend.Service.CustomUserServiceImplementation;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.lang.reflect.Array;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Collections;
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserServiceImplementation customUserServiceImplementation) throws Exception {
//      http.csrf(customizer -> customizer.disable())
//              .authorizeHttpRequests(request -> request
//                      .anyRequest().authenticated())
//              .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return http.build();
//    }
//
//    //becrypt the password to
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//        return  new BCryptPasswordEncoder();
//    }
//
//
//}