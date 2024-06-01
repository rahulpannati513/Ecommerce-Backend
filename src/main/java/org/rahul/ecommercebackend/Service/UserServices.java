package org.rahul.ecommercebackend.Service;


import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices  implements  UserService{

    @Autowired
    private UserRepository userRepo;


   private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    public User saveUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(java.time.LocalDateTime.now());
        System.out.println(user.getPassword());
        return userRepo.save(user);
    }
//    // create a method which will return the user by email if there is no user with that email then throw an exception and password is der
//    public User findUserByEmail(String userEmail) throws UserException {
//        User user = userRepo.findByEmail(userEmail);
//        if(user == null){
//            throw new UserException("User Not Found with Email "+userEmail);
//        }
//        return user;
//    }



}
