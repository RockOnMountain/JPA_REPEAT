package com.study.hexagonal.domain.entity;

import com.study.hexagonal.domain.vo.IP;
import com.study.hexagonal.domain.vo.Id;
import com.study.hexagonal.domain.vo.Location;
import com.study.hexagonal.domain.vo.Model;
import com.study.hexagonal.domain.vo.Vendor;

public class Switch extends Equipment {

    public Switch(Id id, Vendor vendor, Model model, IP ip, Location location) {
        super(id, vendor, model, ip, location);
    }

    // test
}
