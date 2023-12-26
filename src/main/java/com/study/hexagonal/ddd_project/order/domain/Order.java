package com.study.hexagonal.ddd_project.order.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_base")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    @Embedded
    private Orderer orderer;
    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus deliveryStatus;


    public Order(String orderNumber, Orderer orderer) {
        this.orderNumber = orderNumber;
        this.orderer = orderer;
        this.deliveryStatus = DeliveryStatus.WAITING;
    }


    public void order() {
        System.out.println("주문이 진행되고 있습니다.");
        this.deliveryStatus = DeliveryStatus.IN_PROGRESS;
    }
}
