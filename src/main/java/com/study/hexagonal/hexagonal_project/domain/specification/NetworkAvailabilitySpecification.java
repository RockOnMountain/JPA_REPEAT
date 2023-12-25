package com.study.hexagonal.hexagonal_project.domain.specification;

import com.study.hexagonal.hexagonal_project.domain.entity.Equipment;
import com.study.hexagonal.hexagonal_project.domain.entity.Switch;
import com.study.hexagonal.hexagonal_project.domain.exception.GenericSpecificationException;
import com.study.hexagonal.hexagonal_project.domain.specification.common.AbstractSpecification;
import com.study.hexagonal.hexagonal_project.domain.vo.IP;
import com.study.hexagonal.hexagonal_project.domain.vo.Network;


public class NetworkAvailabilitySpecification extends AbstractSpecification<Equipment> {

    private final IP address;
    private final String name;
    private final int cidr;


    public NetworkAvailabilitySpecification(Network network) {
        this.address = network.getNetworkAddress();
        this.name = network.getNetworkName();
        this.cidr = network.getNetworkCidr();
    }


    @Override
    public boolean isSatisfiedBy(Equipment switchNetworks) {
        return switchNetworks != null && this.isNetworkAvailable(switchNetworks);
    }


    private boolean isNetworkAvailable(Equipment switchNetworks) {

        boolean availability = true;

        for(Network network : ((Switch) switchNetworks).getSwitchNetworks()) {

            if(network.getNetworkAddress().equals(this.address) && network.getNetworkName().equals(this.name)
                    && network.getNetworkCidr() == this.cidr) {
                availability = false;
                break;
            }
        }
        return availability;
    }


    @Override
    public void check(Equipment equipment) throws GenericSpecificationException {
        if(!isSatisfiedBy(equipment))
            throw new GenericSpecificationException("This network already exists");
    }


}
