package com.study.jpa.repeat.item.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.study.jpa.repeat.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name = "item_option")
@Entity
public class ItemOption extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String name;
    private int price;
    private int stockQuantity;

}
