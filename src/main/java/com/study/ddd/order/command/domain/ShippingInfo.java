package com.study.ddd.order.command.domain;

import lombok.Getter;

@Getter
public class ShippingInfo {

    private Receiver receiver;
    private Address address;
}
