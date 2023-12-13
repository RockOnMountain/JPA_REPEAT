package com.study.hexagonal.domain.service;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.study.hexagonal.domain.entity.Equipment;
import com.study.hexagonal.domain.entity.Router;
import com.study.hexagonal.domain.vo.Id;

public class RouterService {

    public static List<Router> filterAndRetrieveRouter(List<Router> routers, Predicate<Equipment> routerPredicate) {
        return routers.stream().filter(routerPredicate).collect(Collectors.toList());
    }


    public static Router findById(Map<Id, Router> routers, Id id) {
        return routers.get(id);
    }

}