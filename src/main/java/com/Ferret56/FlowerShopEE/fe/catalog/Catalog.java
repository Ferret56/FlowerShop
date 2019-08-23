package com.Ferret56.FlowerShopEE.fe.catalog;

import com.Ferret56.FlowerShopEE.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private FlowerFilterTypeEnum flowerFilterTypeEnum = FlowerFilterTypeEnum.DEFAULT;

    private String name;

    private BigDecimal startPrice;
    private BigDecimal finalPrice;

    private List<Flower> flowers = new ArrayList<>();

    public FlowerFilterTypeEnum getFlowerFilterTypeEnum() {
        return flowerFilterTypeEnum;
    }

    public void setFlowerFilterTypeEnum(FlowerFilterTypeEnum flowerFilterTypeEnum) {
        this.flowerFilterTypeEnum = flowerFilterTypeEnum;
    }

    public Catalog() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }
}
