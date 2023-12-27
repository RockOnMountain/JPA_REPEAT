package com.study.hexagonal.hexagonal_project.policy;

import lombok.Getter;

@Getter
public class Product {

    private String name;
    private double price;


    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

}
