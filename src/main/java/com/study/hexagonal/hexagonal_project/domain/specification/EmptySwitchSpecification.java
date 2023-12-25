package com.study.hexagonal.hexagonal_project.domain.specification;

import com.study.hexagonal.hexagonal_project.domain.entity.EdgeRouter;
import com.study.hexagonal.hexagonal_project.domain.exception.GenericSpecificationException;
import com.study.hexagonal.hexagonal_project.domain.specification.common.AbstractSpecification;
import org.springframework.util.CollectionUtils;

public class EmptySwitchSpecification extends AbstractSpecification<EdgeRouter> {

    @Override
    public boolean isSatisfiedBy(EdgeRouter edgeRouter) {
        return CollectionUtils.isEmpty(edgeRouter.getSwitches());
    }


    @Override
    public void check(EdgeRouter edgeRouter) {

        if(!isSatisfiedBy(edgeRouter))
            throw new GenericSpecificationException(
                    "It isn't allowed to remove an edge router with a switch attached to it");
    }
}
