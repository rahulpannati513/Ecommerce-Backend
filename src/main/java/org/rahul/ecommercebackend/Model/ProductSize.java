package org.rahul.ecommercebackend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.rahul.ecommercebackend.Model.Product;

@Entity
@Getter
@Setter
@Table(name = "product_size")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Corrected field name from 'name' to 'id'
    private String name; // Corrected type from Long to String
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id") // This column will store the ID of the associated product.
    private Product product;

    public ProductSize() {}

    public ProductSize(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }


}