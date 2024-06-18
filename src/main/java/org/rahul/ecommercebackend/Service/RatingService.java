package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Model.Rating;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Request.RatingRequest;

import java.util.List;

public interface RatingService {

    public Rating createRating(RatingRequest request, User user) throws ProductException;
    public List<Rating> getProductRating(Long productId);

}
