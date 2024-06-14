package org.rahul.ecommercebackend.Model;

import jakarta.persistence.*;

@Embeddable
public class Productsize {

    private Long id;

    private  String size;

    private  int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Productsize() {
    }

    public Productsize(String size, int quantity) {
        this.size = size;
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}