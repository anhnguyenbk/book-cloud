package com.anhnguyen.bookcloud.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Author extends BaseEntity {
    @Id
    private Integer id;
    private String name;
}
