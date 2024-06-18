package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Model.Product;
import org.rahul.ecommercebackend.Model.Review;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Repository.ReviewRepository;
import org.rahul.ecommercebackend.Request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;


    @Override
    public Review createReview(ReviewRequest request, User user) throws ProductException {
        Product product = productService.findProductById(request.getProductId());
        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setReview(request.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
