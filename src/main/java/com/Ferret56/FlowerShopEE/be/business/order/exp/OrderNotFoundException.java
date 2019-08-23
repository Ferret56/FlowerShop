package com.Ferret56.FlowerShopEE.be.business.order.exp;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message){
        super(message);
    }
}
