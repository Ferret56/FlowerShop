package com.Ferret56.FlowerShopEE.test;

import com.Ferret56.FlowerShopEE.be.entity.User;
import com.Ferret56.FlowerShopEE.be.entity.UserRoles;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.awt.event.PaintEvent;
import java.math.BigDecimal;

public class test {
    public static void main(String[] args) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        em.getTransaction().begin();
        User user = new User(1L,"aaa","aaa","aaa", "aaa",BigDecimal.valueOf(10000),10, UserRoles.ADMIN);
        em.persist(user);
        em.getTransaction().commit();
    }
}
