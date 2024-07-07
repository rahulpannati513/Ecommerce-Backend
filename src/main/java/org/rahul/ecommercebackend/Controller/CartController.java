package org.rahul.ecommercebackend.Controller;

import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.Cart;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Request.AddItemRequest;
import org.rahul.ecommercebackend.Response.ApiResponse;
import org.rahul.ecommercebackend.Service.CartService;
import org.rahul.ecommercebackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException {
        String token = jwt.startsWith("Bearer ") ? jwt.substring(7) : jwt;
        User user = userService.findUserProfileByJwt(token);
        Cart cart = cartService.findUserCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest request,
                                                     @RequestHeader("Authorization") String jwt) throws UserException , ProductException {
        String token = jwt.startsWith("Bearer ") ? jwt.substring(7) : jwt;
        User user = userService.findUserProfileByJwt(token);
         cartService.addCartItem(user.getId(), request);

        ApiResponse res = new ApiResponse();
        res.setMessage("Item added to cart successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
