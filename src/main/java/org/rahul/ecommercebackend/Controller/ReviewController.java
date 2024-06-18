package org.rahul.ecommercebackend.Controller;

import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.Review;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Request.ReviewRequest;
import org.rahul.ecommercebackend.Service.ReviewService;
import org.rahul.ecommercebackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request,
                                               @RequestHeader("Authorization") String jwt)throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        Review res = reviewService.createReview(request, user);
        return  new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId)throws  UserException,ProductException{
        List<Review> review = reviewService.getAllReview(productId);
        return new ResponseEntity<>(review,HttpStatus.ACCEPTED);
    }
}
