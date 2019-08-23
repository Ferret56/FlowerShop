package com.Ferret56.FlowerShopEE.be.dao.order;

import com.Ferret56.FlowerShopEE.be.entity.order.Order;
import com.Ferret56.FlowerShopEE.be.entity.order.OrderStatusEnum;

import java.util.List;

public interface OrderDao {
    void createOrder(Order order);
    void removeOrder(Long id);
    List<Order> getAllOrdersByUserId(Long userId);
    List<Order> getAllOrdersByUserId(Long userId, OrderStatusEnum status);
    Order getOrderById(Long id);
    void updateOrder(Order order);
    List<Order> getAllOrders();
    List getAllSortedOrders();
}
