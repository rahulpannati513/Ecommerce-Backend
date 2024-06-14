package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Model.Cart;
import org.rahul.ecommercebackend.Model.CartItem;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Repository.CartRepository;
import org.rahul.ecommercebackend.Request.AddItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation  implements  CartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private  CartItemService cartItemService;
    @Autowired
    private ProductService productService;

    @Override
    public Cart createCart(User user) {
        Cart cart  = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException {
        Cart cart = findUserCart(userId);

        return "";
    }

    @Override
    public Cart findUserCart(Long userId) {
        return null;
    }
}
