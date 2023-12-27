package com.study.hexagonal.hexagonal_project.specification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AndSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> beforeSpecification;
    private final Specification<T> afterSpecification;


    @Override
    public boolean isSatisfied(T t) {
        return beforeSpecification.isSatisfied(t) && afterSpecification.isSatisfied(t);
    }
}
