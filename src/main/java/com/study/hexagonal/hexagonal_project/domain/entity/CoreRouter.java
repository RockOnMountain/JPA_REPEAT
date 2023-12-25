package com.study.hexagonal.hexagonal_project.domain.entity;

import java.util.Map;
import com.study.hexagonal.hexagonal_project.domain.specification.EmptyRouterSpecification;
import com.study.hexagonal.hexagonal_project.domain.specification.EmptySwitchSpecification;
import com.study.hexagonal.hexagonal_project.domain.specification.SameCountrySpecification;
import com.study.hexagonal.hexagonal_project.domain.specification.SameIpSpecification;
import com.study.hexagonal.hexagonal_project.domain.vo.IP;
import com.study.hexagonal.hexagonal_project.domain.vo.Id;
import com.study.hexagonal.hexagonal_project.domain.vo.Location;
import com.study.hexagonal.hexagonal_project.domain.vo.Model;
import com.study.hexagonal.hexagonal_project.domain.vo.RouterType;
import com.study.hexagonal.hexagonal_project.domain.vo.Vendor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CoreRouter extends Router {

    private final Map<Id, Router> routers;


    @Builder
    public CoreRouter(Id id, Vendor vendor, Model model, IP ip, Location location, RouterType routerType,
            Map<Id, Router> routers) {
        super(id, vendor, model, ip, location, routerType);
        this.routers = routers;
    }


    public Router addRouter(Router anyRouter) {

        SameCountrySpecification sameCountrySpecification = new SameCountrySpecification(this);
        SameIpSpecification sameIpSpecification = new SameIpSpecification(this);

        sameCountrySpecification.check(anyRouter);
        sameIpSpecification.check(anyRouter);

        return this.routers.put(anyRouter.getId(), anyRouter);
    }


    public Router removeRouter(Router anyRouter) {

        EmptyRouterSpecification emptyRouterSpecification = new EmptyRouterSpecification();
        EmptySwitchSpecification emptySwitchSpecification = new EmptySwitchSpecification();

        switch(anyRouter.getRouterType()) {
            case CORE:
                CoreRouter coreRouter = (CoreRouter) anyRouter;
                emptyRouterSpecification.check(coreRouter);
            case EDGE:
                EdgeRouter edgeRouter = (EdgeRouter) anyRouter;
                emptySwitchSpecification.check(edgeRouter);
        }

        return this.routers.remove(anyRouter.getId());
    }


}
