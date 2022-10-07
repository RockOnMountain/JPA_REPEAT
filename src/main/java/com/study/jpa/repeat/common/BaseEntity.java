package com.study.jpa.repeat.common;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

public abstract class BaseEntity {

    /*
        스프링 부트에서 JPA에서는 CamelCase 변수랑 DB SnakeCase 컬럼과 매칭이 된다. (default)
     */

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
