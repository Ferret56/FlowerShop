package com.Ferret56.FlowerShopEE.be.dao.order;


import com.Ferret56.FlowerShopEE.be.entity.order.Order;
import com.Ferret56.FlowerShopEE.be.entity.order.OrderStatusEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {


    @Override
    public void createOrder(Order order) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
        //TODO DATE?????
    }

    @Override
    public void removeOrder(Long id) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Order.class,id));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        TypedQuery<Order> query = (TypedQuery<Order>) em.createQuery("select o from Order  o where o.user.id =:userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public List<Order> getAllOrdersByUserId(Long userId, OrderStatusEnum status){
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        TypedQuery<Order> query = (TypedQuery<Order>) em.createQuery("select o from Order o where o.user.id =:userId and o.status=:status");
        query.setParameter("userId",userId);
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public Order getOrderById(Long id) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        return em.find(Order.class, id);
    }

    @Override
    public void updateOrder(Order order) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        em.getTransaction().begin();
        em.merge(order);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Order> getAllOrders() {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        TypedQuery<Order> query = em.createQuery("from Order", Order.class);
        return query.getResultList();
    }

    @Override
    public List<Order> getAllSortedOrders() {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        Query query =em.createQuery("select o from Order as o order by  o.orderCreateDate desc, o.status");
        return query.getResultList();
    }
}
