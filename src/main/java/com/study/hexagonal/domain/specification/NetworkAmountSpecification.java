package com.study.hexagonal.domain.specification;

import com.study.hexagonal.domain.entity.Equipment;
import com.study.hexagonal.domain.entity.Switch;
import com.study.hexagonal.domain.exception.GenericSpecificationException;
import com.study.hexagonal.domain.specification.common.AbstractSpecification;

public class NetworkAmountSpecification extends AbstractSpecification<Equipment> {

    final static public int MAXIMUM_ALLOWED_NETWORKS = 6;


    @Override
    public boolean isSatisfiedBy(Equipment switchNetwork) {
        return ((Switch) switchNetwork).getSwitchNetworks().size() <= MAXIMUM_ALLOWED_NETWORKS;
    }


    @Override
    public void check(Equipment equipment) throws GenericSpecificationException {
        if(!isSatisfiedBy(equipment))
            throw new GenericSpecificationException("The max number of networks is " + MAXIMUM_ALLOWED_NETWORKS);
    }
}
