package com.Ferret56.FlowerShopEE.fe.dto;

import java.math.BigDecimal;

public class FlowerDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private int amount;


    public FlowerDTO(Long id, String name, BigDecimal price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public FlowerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
