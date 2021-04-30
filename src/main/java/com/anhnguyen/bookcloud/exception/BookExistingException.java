package com.anhnguyen.bookcloud.exception;

/**
 * Exception for the deletion an author whose existing books are referenced.
 */
public class BookExistingException extends RuntimeException {
    public BookExistingException(String message) {
        super(message);
    }
}
