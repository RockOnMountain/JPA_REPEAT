package com.study.hexagonal.domain.vo;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Id {

    private final UUID id;


    public static Id withId(String id) {
        return new Id(UUID.fromString(id));
    }


    public static Id withoutId() {
        return new Id(UUID.randomUUID());
    }
}
