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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.study.jpa.repeat.delivery.enums.DeliveryStatus;
import com.study.jpa.repeat.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@SequenceGenerator(name = "delivery_seq_gen", sequenceName = "delivery_seq")
@Table(name = "delivery")
@Entity
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_seq_gen")
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
