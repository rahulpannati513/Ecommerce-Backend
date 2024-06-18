package org.rahul.ecommercebackend.Request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingRequest {
    private Long productId;
    private double rating;

}
