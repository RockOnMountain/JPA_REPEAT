package com.study.hexagonal.domain.entity;

import java.util.Map;
import com.study.hexagonal.domain.specification.EmptyNetworkSpecification;
import com.study.hexagonal.domain.specification.SameCountrySpecification;
import com.study.hexagonal.domain.specification.SameIpSpecification;
import com.study.hexagonal.domain.vo.IP;
import com.study.hexagonal.domain.vo.Id;
import com.study.hexagonal.domain.vo.Location;
import com.study.hexagonal.domain.vo.Model;
import com.study.hexagonal.domain.vo.RouterType;
import com.study.hexagonal.domain.vo.Vendor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EdgeRouter extends Router {

    private final Map<Id, Switch> switches;


    @Builder
    public EdgeRouter(Id id, Vendor vendor, Model model, IP ip, Location location, RouterType routerType,
            Map<Id, Switch> switches) {
        super(id, vendor, model, ip, location, routerType);
        this.switches = switches;
    }


    public void addSwitch(Switch anySwitch) {

        SameCountrySpecification sameCountrySpecification = new SameCountrySpecification(this);
        SameIpSpecification sameIpSpecification = new SameIpSpecification(this);

        sameCountrySpecification.check(anySwitch);
        sameIpSpecification.check(anySwitch);

        this.switches.put(anySwitch.getId(), anySwitch);
    }


    public Switch removeSwitch(Switch anySwitch) {

        EmptyNetworkSpecification emptyNetworkSpecification = new EmptyNetworkSpecification();
        emptyNetworkSpecification.check(anySwitch);

        return this.switches.remove(anySwitch.getId());
    }
}
