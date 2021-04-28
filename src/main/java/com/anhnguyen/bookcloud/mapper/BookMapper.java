package com.anhnguyen.bookcloud.mapper;

import com.anhnguyen.bookcloud.api.model.BookResponse;
import com.anhnguyen.bookcloud.domain.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    public static List<BookResponse> mapList(List<Book> books) {
        return books.stream().map(book -> map(book)).collect(Collectors.toList());
    }

    public static BookResponse map(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setName(book.getName());
        return bookResponse;
    }
}
