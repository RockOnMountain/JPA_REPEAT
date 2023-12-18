package com.study.ddd.order.command.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Receiver {

    private String name;
    private String phoneNumber;
}
