package org.rahul.ecommercebackend.Model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class Size {

    private String name;
    private int quantity;

    public Size() {
    }


}