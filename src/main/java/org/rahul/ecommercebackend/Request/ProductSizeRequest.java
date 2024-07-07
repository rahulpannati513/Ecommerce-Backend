package org.rahul.ecommercebackend.Request;

import lombok.Data;

@Data
public class ProductSizeRequest {
    private String name;
    private Integer quantity;

    public ProductSizeRequest() {}

    public ProductSizeRequest(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}