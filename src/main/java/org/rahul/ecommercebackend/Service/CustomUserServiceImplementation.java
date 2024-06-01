package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Model.UserPrincipal;
import org.rahul.ecommercebackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        UserDetails user = org.springframework.security.core.userdetails.User.
//                withDefaultPasswordEncoder()
//                .username("rahul")
//                .password("Rahul@123")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = org.springframework.security.core.userdetails.User.
//                withDefaultPasswordEncoder()
//                .username("Admin")
//                .password("Admin@123")
//                .roles("ADMIN")
//                .build();




        User user = repo.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User Not Found");
        }







        return new UserPrincipal(user);
    }
}// this will not create a dummy login form and create a custom login form .....
