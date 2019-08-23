package com.Ferret56.FlowerShopEE.be.dao.flower;


import com.Ferret56.FlowerShopEE.be.entity.flower.Flower;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
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
      //  TypedQuery<Flower> query = (TypedQuery<Flower>) em.createQuery("from Flower ", Flower.class);
        TypedQuery<Flower> query = (TypedQuery<Flower>) em.createQuery("SELECT f from Flower f");
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

    @Override
    public void increaseAllFlowersAmount(int count) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        TypedQuery<Flower> query = (TypedQuery<Flower>) em.createQuery("UPDATE Flower SET amount= amount + :count ");
        query.setParameter("count",count);
        query.executeUpdate();
    }

    @Override
    public List<Flower> getFlowersByName(String name) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        TypedQuery<Flower> query = (TypedQuery<Flower>) em.createQuery("select f from Flower  f where lower(f.name) =: name");
        query.setParameter("name", name.toLowerCase());
        return query.getResultList();

    }

    @Override
    public List<Flower> getFlowersByPriceRange(BigDecimal r1, BigDecimal r2) {
        EntityManager em = Persistence
                .createEntityManagerFactory("data")
                .createEntityManager();
        TypedQuery<Flower> query = (TypedQuery<Flower>) em.createQuery("select f from Flower f where f.price >=:r1 AND f.price <=:r2");
        query.setParameter("r1",r1);
        query.setParameter("r2", r2);
        return query.getResultList();
    }
}
