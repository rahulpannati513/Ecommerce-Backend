package org.rahul.ecommercebackend.Request;

import lombok.*;
import org.rahul.ecommercebackend.Model.Productsize;
import org.rahul.ecommercebackend.Model.Size;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreateProductRequest {
    private String title;
    private String description;
    private int price;
    private int discountedPrice;
    private int discountedPercentage;
    private int quantity;
    private String brand;
    private String color;

    private Set<Productsize> size = new HashSet<>();

    private String imageUrl;

    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;

}
