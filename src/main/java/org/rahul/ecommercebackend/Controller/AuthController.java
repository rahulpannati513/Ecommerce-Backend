package org.rahul.ecommercebackend.Controller;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Filters.JwtFilter;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Repository.UserRepository;
import org.rahul.ecommercebackend.Request.LoginRequest;
import org.rahul.ecommercebackend.Response.AuthResponse;
import org.rahul.ecommercebackend.Service.CustomUserServiceImplementation;
import org.rahul.ecommercebackend.Service.JwtService;

import org.rahul.ecommercebackend.Service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserServiceImplementation customUserServiceImplementation;
    private final BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder(12);
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;
    @Autowired
    private HttpSecurity httpSecurity;

    @Autowired
    private UserServiceImplementation userService;
    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    public AuthController(CustomUserServiceImplementation customUserServiceImplementation, JwtService jwtService, UserDetailsService userDetailsService, JwtFilter jwtFilter) {
        this.customUserServiceImplementation = customUserServiceImplementation;

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Autowired
    private UserRepository userRepo;


    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return "Hello " + currentPrincipalName;
    }



    @GetMapping("/token-expiry")
    public ResponseEntity<String> getTokenExpiry(@RequestHeader("Authorization") String token) {
        // Remove the "Bearer " prefix
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Parse the token
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtService.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        // Get the expiration date
        Date expiration = claims.getExpiration();

        // Calculate the remaining time in milliseconds
        long remainingTimeMillis = expiration.getTime() - System.currentTimeMillis();
        // Convert the remaining time to hours and minutes
        long hours = TimeUnit.MILLISECONDS.toHours(remainingTimeMillis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTimeMillis) % 60;

        String remainingTime = hours + " hours and " + minutes + " minutes";
        return new ResponseEntity<>(remainingTime,HttpStatus.CREATED);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUserHandler(@RequestBody User user) throws UserException{

        System.out.println("signup api called");


        return  userServiceImplementation.saveUser(user);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) {
        // Load UserPrincipal by username
        UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(loginRequest.getEmail());

        // Check if passwords match
        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            return new ResponseEntity<>(new AuthResponse("Invalid password"), HttpStatus.UNAUTHORIZED);
        }

        // If passwords match, authenticate the user
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String jwt = jwtService.generateToken(userDetails.getUsername());

        // Create response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("User Logged In Successfully");

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }



}
