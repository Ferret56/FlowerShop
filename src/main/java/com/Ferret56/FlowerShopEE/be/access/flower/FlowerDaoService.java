package com.Ferret56.FlowerShopEE.be.access.flower;

import com.Ferret56.FlowerShopEE.be.business.flower.exp.FlowerNotFoundException;
import com.Ferret56.FlowerShopEE.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerDaoService {
    void addFlower(Flower flower);
    List<Flower> getAllFlowers();
    Flower getFlower(Long id) throws FlowerNotFoundException;
    void updateFlower(Flower flower);
    void increaseAllFlowersAmount(int count);
    List<Flower> getFlowersByName(String name);
    List<Flower> getFlowersByPriceRange(BigDecimal r1, BigDecimal r2);
}
