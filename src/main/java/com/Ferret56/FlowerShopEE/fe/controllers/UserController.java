package com.Ferret56.FlowerShopEE.fe.controllers;

import com.Ferret56.FlowerShopEE.be.access.flower.FlowerDaoService;
import com.Ferret56.FlowerShopEE.be.mapper.Mapper;
import com.Ferret56.FlowerShopEE.be.business.flower.FlowerBusinessService;
import com.Ferret56.FlowerShopEE.be.access.order.OrderDaoService;
import com.Ferret56.FlowerShopEE.be.entity.order.Order;
import com.Ferret56.FlowerShopEE.be.entity.order.OrderStatusEnum;

import com.Ferret56.FlowerShopEE.fe.catalog.Catalog;
import com.Ferret56.FlowerShopEE.fe.dto.BasketDTO;
import com.Ferret56.FlowerShopEE.fe.dto.FlowerDTO;
import com.Ferret56.FlowerShopEE.fe.dto.UserDTO;

import com.Ferret56.FlowerShopEE.fe.catalog.FlowerFilterTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class UserController {


    @Autowired
    private OrderDaoService orderDaoService;
    @Autowired
    private FlowerBusinessService flowerBusinessService;
    @Autowired
    private FlowerDaoService flowerDaoService;
    private Mapper mapper = new Mapper();

    @GetMapping("/userPage")
    public String getUserPage(Model model, HttpSession session){

        if(session.getAttribute("currentBasket")==null)
               session.setAttribute("currentBasket", new BasketDTO());

        Catalog flowerCatalog = (Catalog) session.getAttribute("catalog");
        if(flowerCatalog == null)
            flowerCatalog = new Catalog();

         flowerBusinessService.fillCatalog(flowerCatalog);
         session.setAttribute("catalog", flowerCatalog);

        model.addAttribute("flowerList", mapper.mapList(flowerCatalog.getFlowers(), FlowerDTO.class));
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

        Catalog catalog = (Catalog) session.getAttribute("catalog");
        catalog.setFlowerFilterTypeEnum(FlowerFilterTypeEnum.BY_NAME);
        catalog.setName(flowerName);
        flowerBusinessService.fillCatalog(catalog);

        return "redirect:/userPage";
    }

    @PostMapping("filterByRange")
    public String filterByRange(@ModelAttribute("From")String r1,
                                @ModelAttribute("To")String r2,
                                HttpSession session){
        //TODO See parameters r1 and r2 (BigDecimal  ?????)

        Catalog flowerCatalog = (Catalog) session.getAttribute("catalog");
        if(!r1.equals("") && !r2.equals("")) {
            flowerCatalog.setStartPrice(new BigDecimal(r1));
            flowerCatalog.setFinalPrice(new BigDecimal(r2));
            flowerCatalog.setFlowerFilterTypeEnum(FlowerFilterTypeEnum.BY_RANGE);
        }
        else
            flowerCatalog.setFlowerFilterTypeEnum(FlowerFilterTypeEnum.DEFAULT);


        return "redirect:/userPage";
    }

    @GetMapping("userPage/resetFilter")
    public String resetFilter(HttpSession session){
        Catalog flowerCatalog = (Catalog) session.getAttribute("catalog");
        flowerCatalog.setFlowerFilterTypeEnum(FlowerFilterTypeEnum.DEFAULT);

        return "redirect:/userPage";
    }

    @GetMapping("/viewUnpaidOrders")
    public String viewUnpaidOrders(Model model,HttpSession session){
        UserDTO currentUser = (UserDTO)session.getAttribute("currentUser");
        List<Order> orderList = orderDaoService.getAllOrdersByUserId(currentUser.getId(), OrderStatusEnum.CREATED);
        model.addAttribute("ordersList", orderList);
        return "UnpaidOrders";

    }
    @GetMapping("/back")
    public String back(HttpSession session){
        session.setAttribute("flowerFilter", FlowerFilterTypeEnum.DEFAULT);
        return "redirect:/userPage";
    }

}
