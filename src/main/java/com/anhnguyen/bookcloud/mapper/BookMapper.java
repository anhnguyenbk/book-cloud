package com.anhnguyen.bookcloud.mapper;

import com.anhnguyen.bookcloud.api.model.BookResponse;
import com.anhnguyen.bookcloud.domain.Book;
import com.anhnguyen.bookcloud.util.DateUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * BookMapper is responsible for converting from Book entity (service layer) to BookResponse (view layer)
 */
public class BookMapper {
    /**
     * Convert a List of Book entity to List of BookResponse
     * @param books
     * @return
     */
    public static List<BookResponse> mapList(List<Book> books) {
        return books.stream().map(book -> map(book)).collect(Collectors.toList());
    }

    /**
     * Convert single Book entity to single BookResponse
     * @param book
     * @return
     */
    public static BookResponse map(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setType(book.getType().toString());
        bookResponse.setDescription(book.getDescription());
        bookResponse.setPublishDate(book.getPublishDate());
        bookResponse.setAuthors(AuthorMapper.mapList(book.getAuthors()));
        bookResponse.setCreatedAt(DateUtils.toOffsetDateTime(book.getCreatedAt()));
        bookResponse.setUpdatedAt(DateUtils.toOffsetDateTime(book.getUpdatedAt()));
        return bookResponse;
    }
}
