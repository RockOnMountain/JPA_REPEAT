package com.study.jpa.repeat.member.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name = "member_base")
@Entity
public class Member {

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


}
