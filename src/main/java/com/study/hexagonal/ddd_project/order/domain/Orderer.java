package com.study.hexagonal.ddd_project.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Orderer {

    private String name;
    private String email;
    private String address;


    public Orderer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
