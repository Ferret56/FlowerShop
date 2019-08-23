package com.Ferret56.FlowerShopEE.be.business.order.exp;

public class OrderCreationErrorException extends Exception {
    public OrderCreationErrorException(String message){
        super(message);
    }
}
