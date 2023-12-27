package com.study.hexagonal.hexagonal_project.policy;

public class GradeDiscountPolicy implements DiscountPolicy {


    @Override
    public double discount(Customer customer, Product product) {

        if(customer.getGrade() == CustomerGrade.GOLD) {
            return product.getPrice() * 0.5;
        }

        if(customer.getGrade() == CustomerGrade.SILVER) {
            return product.getPrice() * 0.7;
        }

        return product.getPrice() * 0.9;
    }
}
