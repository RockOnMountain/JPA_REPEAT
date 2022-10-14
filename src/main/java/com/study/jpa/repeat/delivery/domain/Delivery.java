package com.study.jpa.repeat.delivery.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.study.jpa.repeat.delivery.enums.DeliveryStatus;
import com.study.jpa.repeat.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name = "delivery")
@Entity
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
