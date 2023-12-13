package com.study.hexagonal.domain.specification.common;

import com.study.hexagonal.domain.exception.GenericSpecificationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AndSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> specification1;
    private final Specification<T> specification2;


    @Override
    public boolean isSatisfiedBy(T t) {
        return specification1.isSatisfiedBy(t) && specification2.isSatisfiedBy(t);
    }


    @Override
    public void check(T t) throws GenericSpecificationException {

    }
}
