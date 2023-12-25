package com.study.hexagonal.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "account")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int money;


    public Account(String name, int money) {
        this.name = name;
        this.money = money;
    }


    public int decreaseMoney(int money) {

        if(this.money - money < 0) {
            throw new IllegalArgumentException("이건 아니지~");
        }

        return this.money -= money;
    }
}
