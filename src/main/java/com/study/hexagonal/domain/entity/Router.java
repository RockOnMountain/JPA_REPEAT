package com.study.hexagonal.domain.entity;

import java.util.function.Predicate;
import com.study.hexagonal.domain.vo.IP;
import com.study.hexagonal.domain.vo.Id;
import com.study.hexagonal.domain.vo.Location;
import com.study.hexagonal.domain.vo.Model;
import com.study.hexagonal.domain.vo.RouterType;
import com.study.hexagonal.domain.vo.Vendor;
import lombok.Getter;

@Getter
public abstract class Router extends Equipment {

    protected final RouterType routerType;


    public Router(Id id, Vendor vendor, Model model, IP ip, Location location, RouterType routerType) {
        super(id, vendor, model, ip, location);
        this.routerType = routerType;
    }


    public static Predicate<Equipment> getRouterTypePredicate(RouterType routerType) {
        return router -> ((Router) router).getRouterType().equals(routerType);
    }


    public static Predicate<Equipment> getModelPredicate(Model model) {
        return equipment -> equipment.getModel().equals(model);
    }


    public static Predicate<Equipment> getCountryPredicate(Location location) {
        return equipment -> equipment.location.getCountry().equals(location.getCountry());
    }

}
