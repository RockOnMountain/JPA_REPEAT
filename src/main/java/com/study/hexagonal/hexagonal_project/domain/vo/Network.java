package com.study.hexagonal.hexagonal_project.domain.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder
public class Network {

    private final IP networkAddress;
    private final String networkName;
    private final int networkCidr; // CIDR : 2개의 숫자로 구성된 네트워크 주소 표기법


    public Network(IP networkAddress, String networkName, int networkCidr) {

        if(networkCidr < 1 || networkCidr > 32) {
            throw new IllegalArgumentException("Invalid CIDR value");
        }

        this.networkAddress = networkAddress;
        this.networkName = networkName;
        this.networkCidr = networkCidr;
    }
}
