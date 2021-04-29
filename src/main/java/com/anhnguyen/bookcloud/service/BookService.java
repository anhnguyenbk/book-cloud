package com.anhnguyen.bookcloud.service;

import com.anhnguyen.bookcloud.api.model.BookRequest;
import com.anhnguyen.bookcloud.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    Book getById(Integer bookId);

    Book create(BookRequest request);

    void delete(Integer bookId);

    Book update(Integer bookId, BookRequest bookRequest);
}
