package com.anhnguyen.bookcloud.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    @CreatedDate
    protected Date createdAt;
    @LastModifiedDate
    protected Date updatedAt;
}
