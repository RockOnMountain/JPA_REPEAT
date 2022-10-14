package com.study.jpa.repeat.member.dto;

import com.study.jpa.repeat.member.domain.Address;
import lombok.Getter;

@Getter
public class MemberDTO {

    private String name;
    private Address address;
}
