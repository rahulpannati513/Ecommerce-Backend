package org.rahul.ecommercebackend.Service;


import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.Address;
import org.rahul.ecommercebackend.Model.Cart;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Repository.AddressRepository;
import org.rahul.ecommercebackend.Repository.CartRepository;
import org.rahul.ecommercebackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImplementation  implements  UserService{

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private AddressRepository addressRepo;
    @Autowired
    private CartRepository cartRepository;


   private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


@Transactional
public ResponseEntity<?> saveUser(User user) {
    // Check if user already exists
    if (userRepo.findByEmail(user.getEmail()) != null) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "User Already Exists with Email " + user.getEmail());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Encode password and set creation time
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setCreatedAt(java.time.LocalDateTime.now());

    // Validate and set default values for Address objects
    if (user.getAddress() != null) {
        user.getAddress().forEach(address -> {
            if (address.getCity() == null) {
                address.setCity("Default City");
            }
            // Additional checks and default settings for other fields can be added here
            address.setUser(user);
        });
    }

    // Optionally, validate PaymentInformation objects here
    // This could involve filtering out empty objects or setting default values

    // Save the user along with addresses and payment information due to cascade settings
    User savedUser = userRepo.save(user);

    // Create and save a new cart for the user
    Cart newCart = new Cart();
    newCart.setUser(savedUser); // Associate the cart with the saved user
    cartRepository.save(newCart); // Save the cart

    // Return the saved user
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
        User user = userRepo.findByEmail(email);
        if(user == null){
            throw new UserException("User Not Found with email id : "+email );
        }
        return user;
    }
}
