package com.Ferret56.FlowerShopEE.be.business.order;

import com.Ferret56.FlowerShopEE.be.entity.order.OrderItem;
import com.Ferret56.FlowerShopEE.fe.dto.BasketDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BasketBusinessService {
    public void  addOrderItem(OrderItem orderItem, BasketDTO basketDTO){
        List<OrderItem> orderItemList = basketDTO.getOrderItemList();
        boolean isAdd = false;
        for(OrderItem item: orderItemList){
            if(item.getFlower().getName().equals(orderItem.getFlower().getName())) {
                item.setAmount(item.getAmount() + orderItem.getAmount());
                isAdd = true;
                break;
            }
        }
        if(!isAdd)
            orderItemList.add(orderItem);

        basketDTO.setPrice(basketDTO.getPrice().add((orderItem.getFlower().getPrice())
                .multiply(BigDecimal.valueOf(orderItem.getAmount()))));

    }
    public void deleteOrderItemByFlowerId(Long id, BasketDTO basketDTO){
         List<OrderItem> orderItemList = basketDTO.getOrderItemList();
        for(OrderItem item : orderItemList){
            if(item.getFlower().getId().equals(id)){
                orderItemList.remove(item);
                basketDTO.setPrice(basketDTO.getPrice().subtract((item.getFlower().getPrice())
                                   .multiply(BigDecimal.valueOf(item.getAmount()))));
                break;
            }
        }
    }
 }



