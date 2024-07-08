package org.rahul.ecommercebackend.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="order_id")
    private String orderId;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    @OneToOne
    private Address shippingAddress;


    @Embedded
    private PaymentDetails paymentDetails  = new PaymentDetails();

    private BigDecimal totalPrice;

    private  Integer totalDiscountedPrice;

    private Integer discount;

    private  String orderStatus;

    private int totalItem;

    private LocalDateTime createdAt;

}
