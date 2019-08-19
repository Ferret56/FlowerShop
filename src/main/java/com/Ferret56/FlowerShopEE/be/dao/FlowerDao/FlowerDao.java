package com.Ferret56.FlowerShopEE.be.dao.FlowerDao;

import com.Ferret56.FlowerShopEE.be.entity.Flower.Flower;

import java.util.List;

public interface FlowerDao {
    void addFlower(Flower flower);
    List<Flower> getAllFlowers();
    Flower getFlower(Long id);
    void updateFlower(Flower flower);
}
