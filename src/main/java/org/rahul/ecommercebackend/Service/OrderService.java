package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Exception.OrderException;
import org.rahul.ecommercebackend.Model.Address;
import org.rahul.ecommercebackend.Model.Order;
import org.rahul.ecommercebackend.Model.User;

import java.util.List;

public interface OrderService {

  public Order createOrder(User user, Address shippingAddress);

  public List<Order> getAllOrders();

  public Order findOrderById(Long orderId) throws OrderException;

  public List<Order> usersOrderHistory(Long userId);

  public Order placedOrder(Long orderId) throws OrderException;

  public Order confirmedOrder(Long orderId)throws OrderException;

  public Order shippedOrder(Long orderId) throws OrderException;

  public Order deliveredOrder(Long orderId) throws OrderException;

  public Order cancledOrder(Long orderId) throws OrderException;

  public void deleteOrder(Long orderId) throws OrderException;

}
