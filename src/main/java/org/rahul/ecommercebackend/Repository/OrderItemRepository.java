package org.rahul.ecommercebackend.Repository;

import org.rahul.ecommercebackend.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
