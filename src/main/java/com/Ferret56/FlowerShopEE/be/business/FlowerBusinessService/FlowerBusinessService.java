package com.Ferret56.FlowerShopEE.be.business.FlowerBusinessService;

import com.Ferret56.FlowerShopEE.be.access.FlowerDaoService.FlowerDaoService;
import com.Ferret56.FlowerShopEE.be.entity.Flower.Flower;
import com.Ferret56.FlowerShopEE.fe.catalogFilter.FlowerFilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowerBusinessService {
    @Autowired
    private FlowerDaoService flowerDaoService;

    public List<Flower> getFlowers(FlowerFilterType flowerFilterType, HttpSession session){
        switch (flowerFilterType){
            case DEFAULT:
                return flowerDaoService.getAllFlowers();
            case BY_NAME:
                List<Flower> flowerList = filterFlowersByName(flowerDaoService.getAllFlowers(),
                                          (String)session.getAttribute("flowerName"));
                if(flowerList.size() == 0) {
                    session.setAttribute("flowerFilter", FlowerFilterType.DEFAULT);
                    break;
                }
                else
                    return flowerList;

            case BY_RANGE:
                List<Flower> flowers = filterFlowerByRange(flowerDaoService.getAllFlowers(),
                                                    (String)session.getAttribute("From"),
                                                      (String)session.getAttribute("To"));
                if(flowers.size() == 0){
                    session.setAttribute("flowerFilter", FlowerFilterType.DEFAULT);
                    break;
                }
                else
                    return flowers;
        }
        return flowerDaoService.getAllFlowers();
    }

   public  List<Flower> filterFlowersByName(List<Flower> flowers, final String name){
       return  flowers.stream()
                      .filter(f->f.getName()
                      .toLowerCase().equals(name.toLowerCase()))
                      .collect(Collectors.toList());
    }
    public List<Flower> filterFlowerByRange(List<Flower> flowers, final String r1, final String r2){
       if(r1.equals("") || r2.equals(""))
           return new ArrayList<Flower>();
        BigDecimal a = new BigDecimal(r1);
        BigDecimal b = new BigDecimal(r2);
       return   flowers.stream()
                       .filter(f-> (f.getPrice().compareTo(a)==1 && (f.getPrice().compareTo(b)== -1)))
                       .collect(Collectors.toList());
    }
}
