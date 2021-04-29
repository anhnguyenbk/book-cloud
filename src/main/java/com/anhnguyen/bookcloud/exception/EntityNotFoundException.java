package com.anhnguyen.bookcloud.exception;

import lombok.Getter;

/**
 * The exception using when an entity not found.
 */
@Getter
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message){
        super(message);
    }
}
