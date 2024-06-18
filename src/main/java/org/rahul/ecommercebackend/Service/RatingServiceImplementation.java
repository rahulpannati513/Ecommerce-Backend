package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Model.Product;
import org.rahul.ecommercebackend.Model.Rating;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Repository.RatingRepository;
import org.rahul.ecommercebackend.Request.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements  RatingService{


    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public Rating createRating(RatingRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(request.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRating(Long productId) {

        return ratingRepository.getAllProductsRating(productId);
    }



}
