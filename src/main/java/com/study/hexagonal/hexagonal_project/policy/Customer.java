package com.study.hexagonal.hexagonal_project.policy;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Customer {

    private String name;
    private CustomerGrade grade;
}
