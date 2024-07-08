package org.rahul.ecommercebackend.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String title;
    private String description;
    private int price;

    @Column(name = "discounted_price")
    private int discountedPrice;

    @Column(name = "discount_percentage")
    private int discountPercent;

    private String brand;
    private String color;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ProductSize> productSize = new HashSet<>();

    private String imageUrl;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Review> reviews;

    @Column(name = "num_ratings")
    private int numRatings;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}