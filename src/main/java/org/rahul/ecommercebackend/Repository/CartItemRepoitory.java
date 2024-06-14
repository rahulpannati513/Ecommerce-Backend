package org.rahul.ecommercebackend.Repository;


import org.rahul.ecommercebackend.Model.Cart;
import org.rahul.ecommercebackend.Model.CartItem;
import org.rahul.ecommercebackend.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepoitory  extends JpaRepository<CartItem,Long> {

    @Query("SELECT ci FROM CartItem ci where ci.cart=:cart and ci.product=:product and ci.size=:size and ci.userId=:userId")
    public CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product, @Param("size") String size, @Param("userId") Long userId);

}
