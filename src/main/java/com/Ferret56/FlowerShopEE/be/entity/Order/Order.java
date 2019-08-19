package com.Ferret56.FlowerShopEE.be.entity.Order;


import com.Ferret56.FlowerShopEE.be.entity.User.User;

import javax.persistence.*;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USER_ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItemList;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date orderCreateDate;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "close_date")
    private Date orderCloseDate;

    @Column(name = "cost")
    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(User user, List<OrderItem> orderItemList, Date orderCreateDate,
                                           Date orderCloseDate, BigDecimal cost,
                                                              OrderStatus status) {
        this.user = user;
        this.orderItemList = orderItemList;
        this.orderCreateDate = orderCreateDate;
        this.orderCloseDate = orderCloseDate;
        this.cost = cost;
        this.status = status;
    }




    public Order(User user, List<OrderItem> orderItemList, BigDecimal cost) {
        this.user = user;
        this.orderItemList = orderItemList;
        this.cost = cost;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Date getOrderCreateDate() {
        return orderCreateDate;
    }

    public void setOrderCreateDate(Date orderCreateDate) {
        this.orderCreateDate = orderCreateDate;
    }

    public Date getOrderCloseDate() {
        return orderCloseDate;
    }

    public void setOrderCloseDate(Date orderCloseDate) {
        this.orderCloseDate = orderCloseDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
