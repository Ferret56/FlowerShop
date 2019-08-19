package com.Ferret56.FlowerShopEE.fe.controllers;


import com.Ferret56.FlowerShopEE.be.Mapper.Mapper;
import com.Ferret56.FlowerShopEE.be.business.OrderBusinessService.OrderBusinessService;
import com.Ferret56.FlowerShopEE.be.access.OrderDaoService.OrderDaoService;
import com.Ferret56.FlowerShopEE.be.business.OrderBusinessService.exp.OrderCreationErrorException;
import com.Ferret56.FlowerShopEE.be.entity.Order.Order;
import com.Ferret56.FlowerShopEE.be.entity.Order.OrderStatus;
import com.Ferret56.FlowerShopEE.be.entity.User.User;
import com.Ferret56.FlowerShopEE.fe.dto.BasketDTO;

import com.Ferret56.FlowerShopEE.fe.dto.UserDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private OrderDaoService orderDaoService;

    private Mapper mapper = new Mapper();

    @PostMapping("/userPage")
    public String saveOrderSession(@RequestParam("FlowerId")Long id,
                                   @RequestParam("Amount")Integer amount,
                                                      HttpSession session){
        if(id!=null && amount!=null && amount>=0) {
            try {
                orderBusinessService.saveItemToBasket(id,amount,session);
            } catch (OrderCreationErrorException e) {
                LOG.error("Order creation error! "  + e.getMessage());
            }
        }
        return "redirect:/userPage";
    }

    @GetMapping("userPage/basket/remove/{id}")
    public String removeItem(@PathVariable("id")Long flowerId, HttpSession session ){
        orderBusinessService.deleteItemFromBasket(flowerId, session);
        return"redirect:/userPage";
    }

    @GetMapping("userPage/clearOrder")
    public String clearOrder(HttpSession session){
        BasketDTO basketDTO = (BasketDTO) session.getAttribute("currentBasket");
        basketDTO.clear();
        return "redirect:/userPage";
    }

    @GetMapping("userPage/createOrder")
    public String createOrder(HttpSession session){
          UserDTO currentUser = (UserDTO)session.getAttribute("currentUser");
          BasketDTO basketDTO = (BasketDTO) session.getAttribute("currentBasket");
          orderBusinessService.createOrder(new Order(mapper.map(currentUser,User.class),
                                                           basketDTO.getOrderItemList(),
                                                           basketDTO.getPrice()),
                                                           OrderStatus.CREATED);
          basketDTO.clear();
          return "redirect:/viewUnpaidOrders";
    }

    @GetMapping("userPage/buy/order/{id}")
    public String buyOrder (@PathVariable("id")Long id, HttpSession session,
                                       RedirectAttributes redirectAttributes){
        try {
            orderBusinessService.buyOrder(orderDaoService.getOrderById(id), session);

        } catch (OrderCreationErrorException e) {
            LOG.error("Order creation error! "  + e.getMessage() );
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
        }

        return "redirect:/viewUnpaidOrders";
    }

    @GetMapping("admin/close/{id}")
    public String closeOrder(@PathVariable("id")Long id){
        orderBusinessService.closeOrder(orderDaoService.getOrderById(id));
        return "redirect:/admin";
    }

    @GetMapping("/viewOrders")
    public String viewOrders(Model model,HttpSession session){
        UserDTO currentUser = (UserDTO)session.getAttribute("currentUser");
        model.addAttribute("ordersList", orderDaoService.getAllOrdersByUserId(currentUser.getId()));
        return "Orders";
    }

    @GetMapping("remove/order/{id}")
    public String removeOrder(@PathVariable("id")Long id){
        orderDaoService.removeOrder(id);
        return "redirect:/viewUnpaidOrders";
    }


}
