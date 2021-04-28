package com.anhnguyen.bookcloud.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Book extends BaseEntity {
    @Id
    protected Integer id;
    private String name;
}
