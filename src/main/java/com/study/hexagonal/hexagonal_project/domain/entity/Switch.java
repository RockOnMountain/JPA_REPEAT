package com.study.hexagonal.hexagonal_project.domain.entity;

import java.util.List;
import java.util.function.Predicate;
import com.study.hexagonal.hexagonal_project.domain.specification.CIDRSpecification;
import com.study.hexagonal.hexagonal_project.domain.specification.NetworkAmountSpecification;
import com.study.hexagonal.hexagonal_project.domain.specification.NetworkAvailabilitySpecification;
import com.study.hexagonal.hexagonal_project.domain.vo.IP;
import com.study.hexagonal.hexagonal_project.domain.vo.Id;
import com.study.hexagonal.hexagonal_project.domain.vo.Location;
import com.study.hexagonal.hexagonal_project.domain.vo.Model;
import com.study.hexagonal.hexagonal_project.domain.vo.Network;
import com.study.hexagonal.hexagonal_project.domain.vo.SwitchType;
import com.study.hexagonal.hexagonal_project.domain.vo.Vendor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Switch extends Equipment {

    private final SwitchType switchType;
    private final List<Network> switchNetworks;


    @Builder
    public Switch(Id id, Vendor vendor, Model model, IP ip, Location location, SwitchType switchType,
            List<Network> switchNetworks) {
        super(id, vendor, model, ip, location);
        this.switchType = switchType;
        this.switchNetworks = switchNetworks;
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
