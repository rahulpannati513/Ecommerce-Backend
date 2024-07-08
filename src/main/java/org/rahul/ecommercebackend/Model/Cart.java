package org.rahul.ecommercebackend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<CartItem> cartItems = new HashSet<>();

    @Column(name = "total_price", precision = 19, scale = 2)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Column(name = "total_item")
    private int totalItem;

    private int totalDiscountedPrice;
    private int discount;


}