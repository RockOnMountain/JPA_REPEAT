package com.study.hexagonal.ddd_project.order.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "shipping_info")
@Entity
public class ShippingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private String address;
    private String name;


    @Builder
    private ShippingInfo(String orderNumber, String address, String name) {
        this.orderNumber = orderNumber;
        this.address = address;
        this.name = name;
    }
}
