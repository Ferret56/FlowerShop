package com.Ferret56.FlowerShopEE.be.access.flower;

import com.Ferret56.FlowerShopEE.be.business.flower.exp.FlowerNotFoundException;
import com.Ferret56.FlowerShopEE.be.dao.flower.FlowerDao;
import com.Ferret56.FlowerShopEE.be.entity.flower.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FlowerDaoServiceImpl implements FlowerDaoService {

    @Autowired
    private FlowerDao dao;


    @Override
    public void addFlower(Flower flower) {
        dao.addFlower(flower);
    }

    @Override
    public List<Flower> getAllFlowers() {
        return dao.getAllFlowers();
    }

    @Override
    public Flower getFlower(Long id) throws FlowerNotFoundException {
        Flower flower = dao.getFlower(id);
        if(flower == null)
            throw new FlowerNotFoundException("flower with id{" + "} is not found!");
        return flower;

    }

    @Override
    public void updateFlower(Flower flower) {
          dao.updateFlower(flower);
    }

    @Override
    public void increaseAllFlowersAmount(int count) {
               dao.increaseAllFlowersAmount(count);
    }

    @Override
    public List<Flower> getFlowersByName(String name) {
        List<Flower> flowers = dao.getFlowersByName(name);
        if(flowers.size() == 0)
            return dao.getAllFlowers();
        return flowers;
    }

    @Override
    public List<Flower> getFlowersByPriceRange(BigDecimal r1, BigDecimal r2) {
        return dao.getFlowersByPriceRange(r1,r2);
    }
}
