package com.study.hexagonal.hexagonal_project.domain.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Location {

    private final String address;
    private final String city;
    private final String state;
    private final int zipCode;
    private final String country;

    private final float latitude; // 위도
    private final float longitude; // 경도
}
