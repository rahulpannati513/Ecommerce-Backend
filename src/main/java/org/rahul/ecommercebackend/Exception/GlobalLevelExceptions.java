package org.rahul.ecommercebackend.Exception;

import org.rahul.ecommercebackend.Model.Cart;
import org.rahul.ecommercebackend.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalLevelExceptions {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiResponse> handleUserException(UserException e) {
        ApiResponse response = new ApiResponse();
        response.setMessage(e.getMessage());
        response.setStatus(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Object> handleProductException(ProductException ex) {
        // Create a response structure
        ApiResponse response = new ApiResponse();
        response.setStatus(false);
        response.setMessage(ex.getMessage());
        // Return ResponseEntity
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        ApiResponse response = new ApiResponse();
        response.setStatus(false);
        response.setMessage("An error occurred: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ApiResponse> handleOrderException(OrderException e){
        ApiResponse response = new ApiResponse();
        response.setMessage(e.getMessage());
        response.setStatus(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartItemException.class)
    public ResponseEntity<ApiResponse> handleCartItemException(CartItemException e){
        ApiResponse response = new ApiResponse();
        response.setMessage(e.getMessage());
        response.setStatus(false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
