package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.CartItemException;
import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.Cart;
import org.rahul.ecommercebackend.Model.CartItem;
import org.rahul.ecommercebackend.Model.Product;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Repository.CartItemRepoitory;
import org.rahul.ecommercebackend.Repository.CartRepository;
import org.rahul.ecommercebackend.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImplementation implements  CartItemService {

    @Autowired
    private CartItemRepoitory cartItemRepoitory;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

        return cartItemRepoitory.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(id);
        User user = userService.findUserById(item.getUserId());

        if (user.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());

        }
        return cartItemRepoitory.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {

        return cartItemRepoitory.isCartItemExist(cart, product, size, userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {

        CartItem cartItem = findCartItemById(cartItemId);
        User user = userService.findUserById(cartItem.getUserId());

        User reqUser = userService.findUserById(userId);

        if(user.getId().equals(reqUser.getId()))
            cartItemRepoitory.deleteById(cartItemId);
        else
            throw new CartItemException("User not authorized to delete this cart item");

    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt = cartItemRepoitory.findById(cartItemId);

        if(opt.isPresent())
            return opt.get();
        else
            throw new CartItemException("Cart item not found");
    }
}
