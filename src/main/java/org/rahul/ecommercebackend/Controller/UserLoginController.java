package org.rahul.ecommercebackend.Controller;


import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = userService.findUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
