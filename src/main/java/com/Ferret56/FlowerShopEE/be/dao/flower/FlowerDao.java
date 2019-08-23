package com.Ferret56.FlowerShopEE.be.dao.flower;

import com.Ferret56.FlowerShopEE.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerDao {
    void addFlower(Flower flower);
    List<Flower> getAllFlowers();
    Flower getFlower(Long id);
    void updateFlower(Flower flower);
    void increaseAllFlowersAmount(int count);
    List<Flower> getFlowersByName(String name);
    List<Flower> getFlowersByPriceRange(BigDecimal r1, BigDecimal r2);
}
