package com.Ferret56.FlowerShopEE.be.dao.UserDao;

import com.Ferret56.FlowerShopEE.be.entity.User.User;


import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.transaction.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void addUser(User user) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public User findUserById(Long id) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        return em.find(User.class,id);
    }

    @Override
    public User findUserByName(String username) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        Query query = em.createQuery("from  User where username =:username");
        query.setParameter("username", username);
        List users = query.getResultList();
        return CollectionUtils.isEmpty(users) ? null : (User) users.get(0);

    }

    @Override
    @Transactional
    public List<User> getAll() {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        TypedQuery<User> query = em.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
          em.getTransaction().begin();
          em.merge(user);
          em.getTransaction().commit();
          em.close();
    }


}
