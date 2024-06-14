package org.rahul.ecommercebackend.Service;

import org.rahul.ecommercebackend.Model.Address;
import org.rahul.ecommercebackend.Model.Order;
import org.rahul.ecommercebackend.Model.User;

public interface OrderService {

  public Order createOrder(User user, Address shippingAddress);

}
