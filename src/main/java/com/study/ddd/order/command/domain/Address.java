package com.study.ddd.order.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Address {

    private String address1;
    private String address2;
    private String zipcode;
}
