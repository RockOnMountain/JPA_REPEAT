package com.study.hexagonal.domain.entity;

import java.util.List;
import java.util.function.Predicate;
import com.study.hexagonal.domain.specification.CIDRSpecification;
import com.study.hexagonal.domain.specification.NetworkAmountSpecification;
import com.study.hexagonal.domain.specification.NetworkAvailabilitySpecification;
import com.study.hexagonal.domain.vo.IP;
import com.study.hexagonal.domain.vo.Id;
import com.study.hexagonal.domain.vo.Location;
import com.study.hexagonal.domain.vo.Model;
import com.study.hexagonal.domain.vo.Network;
import com.study.hexagonal.domain.vo.SwitchType;
import com.study.hexagonal.domain.vo.Vendor;
import lombok.Getter;

@Getter
public class Switch extends Equipment {

    private SwitchType switchType;
    private List<Network> switchNetworks;


    public Switch(Id id, Vendor vendor, Model model, IP ip, Location location) {
        super(id, vendor, model, ip, location);
    }


    public static Predicate<Switch> isSameSwitchType(SwitchType switchType) {
        return switchEntity -> switchEntity.getSwitchType().equals(switchType);
    }


    public boolean addNetwork(Network network) {

        NetworkAvailabilitySpecification networkAvailabilitySpecification =
                new NetworkAvailabilitySpecification(network);
        CIDRSpecification cidrSpecification = new CIDRSpecification();
        NetworkAmountSpecification networkAmountSpecification = new NetworkAmountSpecification();

        cidrSpecification.check(network.getNetworkCidr());
        networkAvailabilitySpecification.check(this);
        networkAmountSpecification.check(this);

        return this.switchNetworks.add(network);
    }


    public boolean removeNetwork(Network network) {
        return this.switchNetworks.remove(network);
    }

}
