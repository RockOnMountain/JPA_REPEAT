package com.study.hexagonal.hexagonal_project.policy;

/*
        손님 : 이름, 등급 - Customer
        물건 : 이름, 가격 - Product
     */

import lombok.RequiredArgsConstructor;

/*
    정책
    - 등급 정책 - Gold(50%), Silver(30%), Bronze(10%)
    - 가격 정책 - 만원 당 천원
 */
@RequiredArgsConstructor
public class PriceService {

   private final DiscountPolicy discountPolicy;


   public double discount(Customer customer, Product product) {
       return discountPolicy.discount(customer, product);
   }

}
