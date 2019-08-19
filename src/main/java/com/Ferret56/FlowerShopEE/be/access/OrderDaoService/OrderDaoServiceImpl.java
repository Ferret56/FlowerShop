package com.Ferret56.FlowerShopEE.be.access.OrderDaoService;

import com.Ferret56.FlowerShopEE.be.dao.OrderDao.OrderDao;
import com.Ferret56.FlowerShopEE.be.entity.Order.Order;
import com.Ferret56.FlowerShopEE.be.entity.Order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderDaoServiceImpl implements OrderDaoService {

    @Autowired
    private OrderDao dao;

    @Override
    public void createOrder(Order order) {
        dao.createOrder(order);
    }

    @Override
    public void removeOrder(Long id) {
         dao.removeOrder(id);
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {
        return dao.getAllOrdersByUserId(userId);
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long userId, OrderStatus status) {
        return dao.getAllOrdersByUserId(userId, status);
    }

    @Override
    public Order getOrderById(Long id) {
        return  dao.getOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        dao.updateOrder(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return dao.getAllOrders();
    }

    @Override
    public List<Order> getAllSortedOrders() {
        return dao.getAllSortedOrders();
    }
}
