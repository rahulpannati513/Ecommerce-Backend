package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.OrderException;
import org.rahul.ecommercebackend.Model.Address;
import org.rahul.ecommercebackend.Model.Order;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplementation implements  OrderService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ProductService productService;


    @Override
    public Order createOrder(User user, Address shippingAddress) {

        return null;
    }


    public Order findOrderById(Long orderId) throws OrderException{
        return null;
    }
}
