package com.Ferret56.FlowerShopEE.be.entity.Order;


import com.Ferret56.FlowerShopEE.be.entity.Flower.Flower;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order userOrder;

    @OneToOne
    private Flower flower;

    @Column(name = "amount")
    private int amount;


    public OrderItem( Flower flower, int amount) {
        this.flower = flower;
        this.amount = amount;
    }

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(Order userOrder) {
        this.userOrder = userOrder;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
