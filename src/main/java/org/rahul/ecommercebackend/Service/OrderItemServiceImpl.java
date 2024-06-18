package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Model.OrderItem;
import org.rahul.ecommercebackend.Repository.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {


        return orderItemRepository.save(orderItem);
    }
}
