package org.rahul.ecommercebackend.Request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductSizeRequest {
    // Getters and setters
    private String name;
    private Integer quantity;
    private Long productId; // This will be used to associate the ProductSize with a Product

    // No-argument constructor
    public ProductSizeRequest() {}

    // Constructor with all parameters
    public ProductSizeRequest(String name, Integer quantity, Long productId) {
        this.name = name;
        this.quantity = quantity;
        this.productId = productId;
    }

}