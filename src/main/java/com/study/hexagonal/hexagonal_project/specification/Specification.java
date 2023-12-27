package com.study.hexagonal.hexagonal_project.specification;

public interface Specification<T> {

    boolean isSatisfied(T t);
    Specification<T> and(Specification<T> specification);

}
