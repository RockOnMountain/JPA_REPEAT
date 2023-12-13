package com.study.hexagonal.domain.entity;

import java.util.Map;
import com.study.hexagonal.domain.vo.IP;
import com.study.hexagonal.domain.vo.Id;
import com.study.hexagonal.domain.vo.Location;
import com.study.hexagonal.domain.vo.Model;
import com.study.hexagonal.domain.vo.RouterType;
import com.study.hexagonal.domain.vo.Vendor;

public class EdgeRouter extends Router {

    private Map<Id, >

    public EdgeRouter(Id id, Vendor vendor, Model model, IP ip, Location location, RouterType routerType) {
        super(id, vendor, model, ip, location, routerType);
    }
}
