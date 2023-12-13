package com.study.hexagonal.domain.specification;

import com.study.hexagonal.domain.exception.GenericSpecificationException;
import com.study.hexagonal.domain.specification.common.AbstractSpecification;

public class CIDRSpecification extends AbstractSpecification<Integer> {

    final static public int MINIMUM_ALLOWED_CIDR = 8;


    @Override
    public boolean isSatisfiedBy(Integer cidr) {
        return cidr >= MINIMUM_ALLOWED_CIDR;
    }


    @Override
    public void check(Integer cidr) throws GenericSpecificationException {

        if(!isSatisfiedBy(cidr)) {
            throw new GenericSpecificationException("CIDR is below " + CIDRSpecification.MINIMUM_ALLOWED_CIDR);
        }
    }
}
