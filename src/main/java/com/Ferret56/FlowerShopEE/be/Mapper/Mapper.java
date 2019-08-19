package com.Ferret56.FlowerShopEE.be.Mapper;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private DozerBeanMapper mapper = new DozerBeanMapper();

    public <T> T map(Object source, Class<T> destination){
        return mapper.map(source,destination);
    }

    public <T,U> List<U> mapList(List<T> source, Class<U> destination){
        List<U> mapperList = new ArrayList<>();
        for(T e : source)
            mapperList.add(mapper.map(e,destination));
        return mapperList;
   }

}
