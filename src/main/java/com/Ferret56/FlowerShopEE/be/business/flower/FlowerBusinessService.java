package com.Ferret56.FlowerShopEE.be.business.flower;

import com.Ferret56.FlowerShopEE.be.access.flower.FlowerDaoService;
import com.Ferret56.FlowerShopEE.fe.catalog.Catalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowerBusinessService {
    @Autowired
    private FlowerDaoService flowerDaoService;

    public void fillCatalog(Catalog catalog){
        switch (catalog.getFlowerFilterTypeEnum()){
            case DEFAULT:
                catalog.setFlowers(flowerDaoService.getAllFlowers());
                break;
            case BY_NAME:
                catalog.setFlowers(flowerDaoService.getFlowersByName(catalog.getName()));
                break;
            case BY_RANGE:
                catalog.setFlowers(flowerDaoService.getFlowersByPriceRange(catalog.getStartPrice(),
                                                                            catalog.getFinalPrice()));
                break;
        }
    }

}
