package com.Ferret56.FlowerShopEE.be.access.FlowerDaoService;

import com.Ferret56.FlowerShopEE.be.business.FlowerBusinessService.exp.FlowerNotFoundException;
import com.Ferret56.FlowerShopEE.be.dao.FlowerDao.FlowerDao;
import com.Ferret56.FlowerShopEE.be.entity.Flower.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new FlowerNotFoundException("Flower with id{" + "} is not found!");
        return flower;

    }

    @Override
    public void updateFlower(Flower flower) {
          dao.updateFlower(flower);
    }
}
