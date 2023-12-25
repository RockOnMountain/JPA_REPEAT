package com.study.hexagonal.hexagonal_project.domain.specification;

import com.study.hexagonal.hexagonal_project.domain.entity.CoreRouter;
import com.study.hexagonal.hexagonal_project.domain.exception.GenericSpecificationException;
import com.study.hexagonal.hexagonal_project.domain.specification.common.AbstractSpecification;
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
