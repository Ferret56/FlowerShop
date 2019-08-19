package com.Ferret56.FlowerShopEE.be.dao.FlowerDao;


import com.Ferret56.FlowerShopEE.be.entity.Flower.Flower;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class FlowerDaoImpl implements FlowerDao{

    @Override
    public void addFlower(Flower flower) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        em.getTransaction().begin();
        em.persist(flower);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Flower> getAllFlowers() {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Flower> query = em.createQuery("from Flower", Flower.class);
        em.getTransaction().commit();
        return query.getResultList();
    }

    @Override
    public Flower getFlower(Long id) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        em.getTransaction().begin();
        Flower flower = em.find(Flower.class,id);
        em.getTransaction().commit();
        em.close();
        return flower;
    }

    @Override
    public void updateFlower(Flower flower) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        em.getTransaction().begin();
        em.merge(flower);
        em.getTransaction().commit();
        em.close();
    }
}
