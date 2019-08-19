package com.Ferret56.FlowerShopEE.fe.controllers;

import com.Ferret56.FlowerShopEE.be.Mapper.Mapper;
import com.Ferret56.FlowerShopEE.be.business.FlowerBusinessService.FlowerBusinessService;
import com.Ferret56.FlowerShopEE.be.access.OrderDaoService.OrderDaoService;
import com.Ferret56.FlowerShopEE.be.entity.Order.Order;
import com.Ferret56.FlowerShopEE.be.entity.Order.OrderStatus;

import com.Ferret56.FlowerShopEE.fe.dto.BasketDTO;
import com.Ferret56.FlowerShopEE.fe.dto.FlowerDTO;
import com.Ferret56.FlowerShopEE.fe.dto.UserDTO;

import com.Ferret56.FlowerShopEE.fe.catalogFilter.FlowerFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private OrderDaoService orderDaoService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    private Mapper mapper = new Mapper();

    @GetMapping("/userPage")
    public String getUserPage(Model model, HttpSession session){

        if(session.getAttribute("currentBasket")==null)
               session.setAttribute("currentBasket", new BasketDTO());

        FlowerFilterType flowerFilterType = (FlowerFilterType)session.getAttribute("flowerFilter");
        if(flowerFilterType == null)
             session.setAttribute("flowerFilter", FlowerFilterType.DEFAULT );

        model.addAttribute("flowerList", mapper.mapList(flowerBusinessService.getFlowers((FlowerFilterType)session
                                                        .getAttribute("flowerFilter"), session), FlowerDTO.class));
        return "UserPage";
    }


    @GetMapping("/admin")
    public String getAdminPage(Model model){
        model.addAttribute("ordersList", orderDaoService.getAllOrders());
        return "Admin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("userPage/filterByName")
    public String filterByName(@ModelAttribute("flowerName")String flowerName,
                                                          HttpSession session){
        session.setAttribute("flowerName", flowerName);
        session.setAttribute("flowerFilter", FlowerFilterType.BY_NAME);
        return "redirect:/userPage";
    }

    @PostMapping("filterByRange")
    public String filterByRange(@ModelAttribute("From") String r1,
                                @ModelAttribute("To")String r2,
                                HttpSession session){
        //TODO See parameters r1 and r2 (BigDecimal  ?????)
        session.setAttribute("From", r1);
        session.setAttribute("To", r2);
        session.setAttribute("flowerFilter", FlowerFilterType.BY_RANGE);
        return "redirect:/userPage";
    }

    @GetMapping("userPage/resetFilter")
    public String resetFilter(HttpSession session){
        session.setAttribute("flowerFilter", FlowerFilterType.DEFAULT);
        return "redirect:/userPage";
    }

    @GetMapping("/viewUnpaidOrders")
    public String viewUnpaidOrders(Model model,HttpSession session){
        UserDTO currentUser = (UserDTO)session.getAttribute("currentUser");
        List<Order> orderList = orderDaoService.getAllOrdersByUserId(currentUser.getId(), OrderStatus.CREATED);
        model.addAttribute("ordersList", orderList);
        return "UnpaidOrders";

    }
    @GetMapping("/back")
    public String back(HttpSession session){
        session.setAttribute("flowerFilter", FlowerFilterType.DEFAULT);
        return "redirect:/userPage";
    }

}
