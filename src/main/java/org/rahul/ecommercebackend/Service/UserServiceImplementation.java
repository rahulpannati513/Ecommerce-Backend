package org.rahul.ecommercebackend.Service;


import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImplementation  implements  UserService{

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private  JwtService jwtService;


   private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    public ResponseEntity<?> saveUser(User user) {
        if(userRepo.findByEmail(user.getEmail()) != null){
            System.out.println("User Already Exists with Email in save user "+user.getEmail());
            Map<String, String> error = new HashMap<>();
            error.put("error", "User Already Exists with Email "+user.getEmail());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(java.time.LocalDateTime.now());
        System.out.println(user.getPassword());
        User savedUser = userRepo.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepo.findById(userId);

        if(user.isPresent()){
            return user.get();
        }
         throw new UserException("User Not Found with email id : "+userId );
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtService.extractUserName(jwt);
        System.out.println(email+"_______coreect ichinda_______________________");
        User user = userRepo.findByEmail(email);
        System.out.println(user);
        System.out.println("_______________________________________");
        if(user == null){
            throw new UserException("User Not Found with email id : "+email );
        }
        return user;
    }
}
