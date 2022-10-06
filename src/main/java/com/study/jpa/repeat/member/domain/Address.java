package com.study.jpa.repeat.member.domain;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable // @Embeddable 은 생략이 가능합니다. -> 명시적으로 보여주는 것도 좋아보입니다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;


    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
