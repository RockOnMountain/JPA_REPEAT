package com.study.hexagonal.hexagonal_project.domain.specification;

import com.study.hexagonal.hexagonal_project.domain.entity.Switch;
import com.study.hexagonal.hexagonal_project.domain.exception.GenericSpecificationException;
import com.study.hexagonal.hexagonal_project.domain.specification.common.AbstractSpecification;
import org.springframework.util.CollectionUtils;

public class EmptyNetworkSpecification extends AbstractSpecification<Switch> {

    @Override
    public boolean isSatisfiedBy(Switch switchNetwork) {
        return CollectionUtils.isEmpty(switchNetwork.getSwitchNetworks());
    }


    @Override
    public void check(Switch aSwitch) throws GenericSpecificationException {

        if(!isSatisfiedBy(aSwitch)) {
            throw new GenericSpecificationException(
                    "It's not possible to remove a switch with networks attached to it");
        }
    }
}
