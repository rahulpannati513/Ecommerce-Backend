package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Model.Review;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest request, User user) throws ProductException;

    public List<Review> getAllReview(Long productId);

}
