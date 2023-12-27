package com.study.hexagonal.hexagonal_project.policy;

public interface DiscountPolicy {

    double discount(Customer customer, Product product);
}
