package com.study.hexagonal.ddd_project.order.event;

import com.study.hexagonal.ddd_project.order.domain.Orderer;
import lombok.Getter;

@Getter
public class DeliveredEvent {

    private final String orderNumber;
    private final String address;
    private final String name;


    public DeliveredEvent(String orderNumber, Orderer orderer) {
        this.orderNumber = orderNumber;
        this.address = orderer.getAddress();
        this.name = orderer.getName();
    }
}
