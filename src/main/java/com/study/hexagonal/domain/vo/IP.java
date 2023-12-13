package com.study.hexagonal.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class IP {

    private final String ipAddress;
    private final Protocol protocol;


    public IP(String ipAddress) {

        if(ipAddress == null) {
            throw new IllegalArgumentException("Null IP address");
        }

        this.ipAddress = ipAddress;
        this.protocol = ipAddress.length() <= 15 ? Protocol.IPV4 : Protocol.IPV6;
    }


    public static IP fromAddress(String ipAddress) {
        return new IP(ipAddress);
    }

    /*
        명세

        객체의 특성을 보장하는데 사용되는 조건 또는 조건식
        명세는 이 조건식을 캡슐화하여 재사용할 수 있다는 특성을 가지고 있다.
     */
}
