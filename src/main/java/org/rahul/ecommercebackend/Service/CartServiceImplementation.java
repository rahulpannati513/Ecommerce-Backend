package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.ProductException;
import org.rahul.ecommercebackend.Exception.UserException;
import org.rahul.ecommercebackend.Model.Cart;
import org.rahul.ecommercebackend.Model.CartItem;
import org.rahul.ecommercebackend.Model.Product;
import org.rahul.ecommercebackend.Model.User;
import org.rahul.ecommercebackend.Repository.CartRepository;
import org.rahul.ecommercebackend.Request.AddItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartServiceImplementation  implements  CartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private  CartItemService cartItemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Override
    public Cart createCart(User user) {
        Cart cart  = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException, UserException {
        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            User user = userService.findUserById(userId);
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        Product product = productService.findProductById(request.getProductId());
        CartItem isPresent = cartItemService.isCartItemExist(cart, product, request.getSize(), userId);

        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUserId(userId);

            int price = request.getQuantity() * product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(request.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }

        // Calculate totals
        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;
        for (CartItem item : cart.getCartItems()) {
            totalPrice += item.getPrice();
            totalDiscountedPrice += item.getDiscountedPrice(); // Assuming getDiscountedPrice() exists and returns the discounted price of the item
            totalItem += item.getQuantity();
        }

        // Update cart with totals
        cart.setTotalPrice(BigDecimal.valueOf(totalPrice));
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItem);

        cartRepository.save(cart); // Save the updated cart

        return "Item added to cart";
    }

    @Override
    public Cart findUserCart(Long userId) {

        Cart userCart = cartRepository.findByUserId(userId);
        System.out.println(userCart+"cart service implementation");
        return userCart;

    }
}
