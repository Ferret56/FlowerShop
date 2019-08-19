package com.Ferret56.FlowerShopEE.fe.dto;

import com.Ferret56.FlowerShopEE.be.entity.Order.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class BasketDTO {
    private List<OrderItem> orderItemList;
    private BigDecimal price;

    public BasketDTO(List<OrderItem> orderItems, BigDecimal price) {
        this.orderItemList = orderItems;
        this.price = price;
    }

    public BasketDTO() {
        orderItemList = new ArrayList<>();
        price = BigDecimal.valueOf(0);
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void clear(){
        orderItemList.clear();
        price = BigDecimal.valueOf(0);
    }
}
