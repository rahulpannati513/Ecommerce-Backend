package org.rahul.ecommercebackend.Service;

import jdk.jshell.spi.ExecutionControl;
import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


public interface UserService {


    public User saveUser(User user);







}
