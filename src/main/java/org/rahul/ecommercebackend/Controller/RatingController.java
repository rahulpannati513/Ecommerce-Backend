package org.rahul.ecommercebackend.Controller;

import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.Rating;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Request.RatingRequest;
import org.rahul.ecommercebackend.Service.RatingService;
import org.rahul.ecommercebackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req,
                                               @RequestHeader("Authorization")String jwt )throws UserException , ProductException{
        User user = userService.findUserProfileByJwt(jwt);
        Rating rating = ratingService.createRating(req, user);
        return  new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductRating(@PathVariable Long productId,
                                                         @RequestHeader("Authorization")String jwt) throws ProductException {
        List<Rating> res = ratingService.getProductRating(productId);

        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }

}
