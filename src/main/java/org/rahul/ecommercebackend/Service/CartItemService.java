package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.CartItemException;
import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.Cart;
import org.rahul.ecommercebackend.Model.CartItem;
import org.rahul.ecommercebackend.Model.Product;
import org.springframework.security.core.userdetails.User;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id,CartItem cartItem)throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;


}
