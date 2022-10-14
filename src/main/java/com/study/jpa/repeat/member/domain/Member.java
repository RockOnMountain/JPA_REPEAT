package com.study.jpa.repeat.member.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.study.jpa.repeat.common.BaseEntity;
import com.study.jpa.repeat.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name = "member_base")
@Entity
public class Member extends BaseEntity {

    /*
        GenerationType.SEQUENCE : H2, Oracle
        GenerationType.IDENTITY : MySql
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
