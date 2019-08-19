package com.Ferret56.FlowerShopEE.be.dao.OrderDao;

import com.Ferret56.FlowerShopEE.be.entity.Order.Order;
import com.Ferret56.FlowerShopEE.be.entity.Order.OrderStatus;

import java.util.List;

public interface OrderDao {
    void createOrder(Order order);
    void removeOrder(Long id);
    List<Order> getAllOrdersByUserId(Long userId);
    List<Order> getAllOrdersByUserId(Long userId, OrderStatus status);
    Order getOrderById(Long id);
    void updateOrder(Order order);
    List<Order> getAllOrders();
    List getAllSortedOrders();
}
