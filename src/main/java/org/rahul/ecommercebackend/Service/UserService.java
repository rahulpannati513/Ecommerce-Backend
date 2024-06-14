package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.User;
import org.springframework.http.ResponseEntity;


public interface UserService {


    public ResponseEntity<?> saveUser(User user) throws UserException;

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;









}
