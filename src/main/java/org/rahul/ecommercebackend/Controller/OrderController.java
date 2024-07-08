package org.rahul.ecommercebackend.Controller;

import org.rahul.ecommercebackend.Exception.OrderException;
import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.Address;
import org.rahul.ecommercebackend.Model.Order;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Service.OrderService;
import org.rahul.ecommercebackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
                                             @RequestHeader("Authorization") String jwt)throws UserException {
        String token = jwt.startsWith("Bearer ") ? jwt.substring(7) : jwt;
        User user = userService.findUserProfileByJwt(token);
        Order orderCreated = orderService.createOrder(user, shippingAddress);


        return new ResponseEntity<>(orderCreated, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>>  userOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException {
        String token = jwt.startsWith("Bearer ") ? jwt.substring(7) : jwt;
        User user = userService.findUserProfileByJwt(token);
        List<Order> orderHistory = orderService.usersOrderHistory(user.getId());

        return new ResponseEntity<>(orderHistory,HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findOrderById(@PathVariable("orderId") Long orderId,
                                               @RequestHeader("Authorization") String jwt) throws UserException, OrderException {
        String token = jwt.startsWith("Bearer ") ? jwt.substring(7) : jwt;
        User user = userService.findUserProfileByJwt(token);
        Order order = orderService.findOrderById(orderId);

        return  new ResponseEntity<>(order,HttpStatus.ACCEPTED);
    }
}
