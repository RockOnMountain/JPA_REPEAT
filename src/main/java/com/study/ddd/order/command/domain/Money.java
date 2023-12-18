package com.study.ddd.order.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Money {

    private int value;


    public Money add(Money money) {
        return new Money(this.value + money.getValue());
    }


    public Money multiply(int multiplier) {
        return new Money(this.value * multiplier);
    }
}
