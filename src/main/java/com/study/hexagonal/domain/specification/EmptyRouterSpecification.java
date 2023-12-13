package com.study.hexagonal.domain.specification;

import com.study.hexagonal.domain.entity.CoreRouter;
import com.study.hexagonal.domain.exception.GenericSpecificationException;
import com.study.hexagonal.domain.specification.common.AbstractSpecification;
import org.springframework.util.CollectionUtils;

public class EmptyRouterSpecification extends AbstractSpecification<CoreRouter> {

    @Override
    public boolean isSatisfiedBy(CoreRouter coreRouter) {
        return CollectionUtils.isEmpty(coreRouter.getRouters());
    }

    @Override
    public void check(CoreRouter coreRouter) {
        if(!isSatisfiedBy(coreRouter))
            throw new GenericSpecificationException("It isn't allowed to remove a core router with other routers attached to it");
    }
}
