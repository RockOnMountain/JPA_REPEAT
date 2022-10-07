package com.study.jpa.repeat.order.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.study.jpa.repeat.common.BaseEntity;
import com.study.jpa.repeat.item.domain.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "order_item")
@Entity
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(mappedBy = "orderItem")
    private List<OrderOption> orderOptions = new ArrayList<>();

}
