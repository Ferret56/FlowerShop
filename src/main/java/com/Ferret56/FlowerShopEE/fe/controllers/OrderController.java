package com.Ferret56.FlowerShopEE.fe.controllers;


import com.Ferret56.FlowerShopEE.be.mapper.Mapper;
import com.Ferret56.FlowerShopEE.be.access.user.UserDaoService;
import com.Ferret56.FlowerShopEE.be.business.order.OrderBusinessService;
import com.Ferret56.FlowerShopEE.be.access.order.OrderDaoService;
import com.Ferret56.FlowerShopEE.be.business.order.exp.OrderCreationErrorException;
import com.Ferret56.FlowerShopEE.be.business.user.exp.UserNotFoundException;
import com.Ferret56.FlowerShopEE.be.entity.order.Order;
import com.Ferret56.FlowerShopEE.be.entity.order.OrderStatusEnum;
import com.Ferret56.FlowerShopEE.be.entity.user.User;
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

    @Autowired
    private UserDaoService userDaoService;

    private Mapper mapper = new Mapper();

    @PostMapping("/userPage")
    public String saveOrderToBasket(@RequestParam("FlowerId")Long id,
                                    @RequestParam("Amount")Integer amount,
                                    HttpSession session, RedirectAttributes redirectAttributes){
        if(id!=null && amount!=null && amount>=0) {
            try {
                BasketDTO basketDTO = (BasketDTO) session.getAttribute("currentBasket");
                UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");
                orderBusinessService.saveItemToBasket(id,amount, basketDTO, userDTO.getDiscount());
            } catch (OrderCreationErrorException e) {
                LOG.error("order creation error! "  + e.getMessage());
                redirectAttributes.addFlashAttribute("informationMessage", e.getMessage());
            }
        }
        return "redirect:/userPage";
    }

    @GetMapping("userPage/basket/remove/{id}")
    public String removeItemFromBasket(@PathVariable("id")Long flowerId, HttpSession session ){
        BasketDTO basketDTO = (BasketDTO) session.getAttribute("currentBasket");
        orderBusinessService.deleteItemFromBasket(flowerId, basketDTO);
        return"redirect:/userPage";
    }

    @GetMapping("userPage/clearOrder")
    public String clearBasket(HttpSession session){
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
                                                           OrderStatusEnum.CREATED);
          basketDTO.clear();
          return "redirect:/viewUnpaidOrders";
    }

    @GetMapping("userPage/buy/order/{id}")
    public String buyOrder (@PathVariable("id")Long id, HttpSession session,
                                       RedirectAttributes redirectAttributes){
        try {
            UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");
            orderBusinessService.buyOrder(orderDaoService.getOrderById(id), mapper.map(userDTO,User.class));
            userDTO.setMoney(userDaoService.findUserById(userDTO.getId()).getMoney());
        } catch (OrderCreationErrorException e) {
            LOG.error("order creation error! "  + e.getMessage() );
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
        } catch (UserNotFoundException e) {
            LOG.error("Error while finding user" + e.getMessage());
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
