package com.Ferret56.FlowerShopEE.be.access.FlowerDaoService;

import com.Ferret56.FlowerShopEE.be.business.FlowerBusinessService.exp.FlowerNotFoundException;
import com.Ferret56.FlowerShopEE.be.entity.Flower.Flower;

import java.util.List;

public interface FlowerDaoService {
    void addFlower(Flower flower);
    List<Flower> getAllFlowers();
    Flower getFlower(Long id) throws FlowerNotFoundException;
    void updateFlower(Flower flower);
}
