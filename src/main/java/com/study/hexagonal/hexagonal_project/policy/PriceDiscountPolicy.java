package com.study.hexagonal.hexagonal_project.policy;

public class PriceDiscountPolicy implements DiscountPolicy{

    @Override
    public double discount(Customer customer, Product product) {

        double discountPrice = ((int) product.getPrice() / 10000) * 1000;
        return product.getPrice() - discountPrice;
    }
}
