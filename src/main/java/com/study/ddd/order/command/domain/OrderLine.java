package com.study.ddd.order.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderLine { // 주문 상품

    private Product product;
    private Money price;
    private int quantity;
    private Money amounts;


    private Money calculateAmounts() {
        return this.price.multiply(this.quantity);
    }


    public int getAmounts() {
        return this.amounts.getValue();
    }


}
