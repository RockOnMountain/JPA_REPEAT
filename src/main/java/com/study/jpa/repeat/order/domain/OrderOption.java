package com.study.jpa.repeat.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.study.jpa.repeat.common.BaseEntity;
import com.study.jpa.repeat.item.domain.ItemOption;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name = "order_option")
@Entity
public class OrderOption extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_option_id")
    private ItemOption itemOption;

    private int price;
    private int count;
}
