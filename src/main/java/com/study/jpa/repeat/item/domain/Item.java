package com.study.jpa.repeat.item.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name = "item")
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


}
