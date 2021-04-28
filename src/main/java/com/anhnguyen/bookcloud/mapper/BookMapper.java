package com.anhnguyen.bookcloud.mapper;

import com.anhnguyen.bookcloud.api.model.BookResponse;
import com.anhnguyen.bookcloud.domain.Book;
import com.anhnguyen.bookcloud.util.EpochTimeUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    public static List<BookResponse> mapList(List<Book> books) {
        return books.stream().map(book -> map(book)).collect(Collectors.toList());
    }

    public static BookResponse map(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setTitle(book.getTitle());
        bookResponse.setType(book.getType().toString());
        bookResponse.setDescription(book.getDescription());
        bookResponse.setCreatedAt(EpochTimeUtils.toEpochTime(book.getCreatedAt()));
        bookResponse.setUpdatedAt(EpochTimeUtils.toEpochTime(book.getUpdatedAt()));
        bookResponse.setPublishDate(EpochTimeUtils.toEpochTime(book.getPublishDate()));
        return bookResponse;
    }
}
