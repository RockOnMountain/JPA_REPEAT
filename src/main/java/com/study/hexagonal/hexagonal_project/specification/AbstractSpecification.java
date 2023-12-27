package com.study.hexagonal.hexagonal_project.specification;

public abstract class AbstractSpecification<T> implements Specification<T> {

    public Specification<T> and(Specification<T> specification) {
        return new AndSpecification<>(this, specification);
    }
}
