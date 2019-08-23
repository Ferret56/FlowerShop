package com.Ferret56.FlowerShopEE.be.business.order;

import com.Ferret56.FlowerShopEE.be.entity.flower.Flower;
import com.Ferret56.FlowerShopEE.be.mapper.Mapper;
import com.Ferret56.FlowerShopEE.be.access.order.OrderDaoService;
import com.Ferret56.FlowerShopEE.be.access.flower.FlowerDaoService;
import com.Ferret56.FlowerShopEE.be.business.flower.exp.FlowerNotFoundException;
import com.Ferret56.FlowerShopEE.be.business.order.exp.OrderCreationErrorException;
import com.Ferret56.FlowerShopEE.be.access.user.UserDaoService;
import com.Ferret56.FlowerShopEE.be.entity.order.Order;
import com.Ferret56.FlowerShopEE.be.entity.order.OrderItem;
import com.Ferret56.FlowerShopEE.be.entity.order.OrderStatusEnum;
import com.Ferret56.FlowerShopEE.be.entity.user.User;

import com.Ferret56.FlowerShopEE.fe.dto.BasketDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderBusinessService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderBusinessService.class);

    @Autowired
    private FlowerDaoService flowerDaoService;
    @Autowired
    private OrderDaoService orderDaoService;
    @Autowired
    private UserDaoService userDaoService;
    @Autowired
    private  BasketBusinessService basketBusinessService;
    private Mapper mapper = new Mapper();

    public void saveItemToBasket(final Long flowerId, final int amount,
                                                    BasketDTO basketDTO,
                                                    int discount)
                                                    throws OrderCreationErrorException {
        try {
            Flower flower = flowerDaoService.getFlower(flowerId);
            if(flower.getAmount() < amount )
                throw new OrderCreationErrorException("Please enter a valid number");
            basketBusinessService.addOrderItem(new OrderItem(flower,amount), basketDTO);
            basketDTO.setPrice( basketDTO.getPrice().multiply(BigDecimal.valueOf((float)(100-discount)/100)).setScale(2, BigDecimal.ROUND_HALF_DOWN));
        } catch (FlowerNotFoundException e) {
           LOG.debug("flower not found "  + e.getMessage());
        }
    }

    public void deleteItemFromBasket(final Long flowerId, BasketDTO basketDTO){
        basketBusinessService.deleteOrderItemByFlowerId(flowerId, basketDTO);
    }

    public void createOrder(Order order, OrderStatusEnum status){
            order.setStatus(status);
            orderDaoService.createOrder(order);
    }

    @Transactional
    public void buyOrder(Order order, User user) throws OrderCreationErrorException {
        if(order.getCost().compareTo(order.getUser().getMoney()) == 1)
            throw new OrderCreationErrorException("order creation error! Do not have enough money");
        order.setStatus(OrderStatusEnum.PAID);
        order.setOrderCreateDate(new Date());

        user.setMoney(order.getUser().getMoney().subtract(order.getCost()));
        userDaoService.updateUser(user);
        orderDaoService.updateOrder(order);
        for(OrderItem item : order.getOrderItemList()){
            Flower flower = item.getFlower();
            flower.setAmount(flower.getAmount() - item.getAmount());
            flowerDaoService.updateFlower(flower);
        }
    }

    public void closeOrder(Order order){
            if(order.getStatus().equals(OrderStatusEnum.PAID)) {
                order.setStatus(OrderStatusEnum.CLOSED);
                order.setOrderCloseDate(new Date());
                orderDaoService.updateOrder(order);
            }
    }
}
