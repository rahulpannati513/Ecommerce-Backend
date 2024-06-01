package org.rahul.ecommercebackend.Controller;


import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Filters.JwtFilter;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Repository.UserRepository;
import org.rahul.ecommercebackend.Request.LoginRequest;
import org.rahul.ecommercebackend.Response.AuthResponse;
import org.rahul.ecommercebackend.Service.CustomUserServiceImplementation;
import org.rahul.ecommercebackend.Service.JwtService;
import org.rahul.ecommercebackend.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
    public AuthController(CustomUserServiceImplementation customUserServiceImplementation, JwtService jwtService, UserDetailsService userDetailsService, JwtFilter jwtFilter) {
        this.customUserServiceImplementation = customUserServiceImplementation;

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Autowired
    private UserRepository userRepo;



    @Autowired
    private UserServices userServices;

    @Autowired
    private AuthenticationManager authenticationManager;








    @GetMapping("/hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return "Hello " + currentPrincipalName;
    }



    @PostMapping("/signup")
    public ResponseEntity<User> createUserHandler(@RequestBody User user) throws UserException{

        System.out.println("signup api called");
     //  String email = user.getEmail();

//        String password = user.getPassword();
//        String firstName = user.getFirstName();
//        String lastName = user.getLastName();
//        String mobile = user.getMobile();
//        String role = user.getRole();

        //LocalDateTime createdAt = LocalDateTime.now();


//        id, created_at, email, first_name, last_name, mobile, password, role


        User createdUser = userServices.saveUser(user);

/*
        User isEmailExist = userRepo.findByEmail(email);

        if(isEmailExist != null){
            throw  new UserException("Email is Already Used with Another Account ");
        }
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(new BCryptPasswordEncoder().encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);

        User savedUser = userRepo.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt("token");
        authResponse.setMessage("User Created Successfully");
*/

        return  new ResponseEntity<User>(createdUser, HttpStatus.CREATED);

    }

//
//    @PostMapping("/signin")
//    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException {
//
//        System.out.println("signin api called");
//
//
//
//
//       // UserDetails usernames = customUserServiceImplementation.loadUserByUsername(username);
//
//        //Authenication token will check the username and password
//        //Authentication manager and Authenticaton provider will check the username and password
//        //Authentication manager will check the username and password
//        //Authentication provider will check the username and password
//        //describe its use case
//        //Authentication authentication = authenticate(username,password);
//        //SecurityContextHolder.getContext().setAuthentication(authentication);
//        //String token = jwtProvider.generateToken(authentication);
//        //AuthResponse authResponse = new AuthResponse();
//        //authResponse.setJwt(token);
//        //authResponse.setMessage("User Logged In Successfully");
//
//
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword() ));
//        AuthResponse authResponse = new AuthResponse();
//        authResponse.setJwt(jwtService.generateToken(loginRequest.getEmail()));
//        authResponse.setMessage("User Logged In Successfully");
//
//        if(authentication.isAuthenticated())
//            return new ResponseEntity<AuthResponse>( authResponse, HttpStatus.OK);
//        else
//            return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
//
//    }




//    private Authentication authenticate(String username, String password) {
//        UserDetails userDetails =  customUserServiceImplementation.loadUserByUsername(username);
//
//        if(userDetails == null){
//            throw new BadCredentialsException("Invalid Username");
//        }
//
//        if(!passwordEncoder.matches(password,userDetails.getPassword())){
//            throw new BadCredentialsException("Invalid Password");
//        }
//
//
//        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//    }








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
