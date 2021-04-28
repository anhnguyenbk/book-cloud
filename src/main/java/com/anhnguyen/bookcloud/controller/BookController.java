package com.anhnguyen.bookcloud.controller;

import com.anhnguyen.bookcloud.api.BooksApi;
import com.anhnguyen.bookcloud.api.model.BookResponse;
import com.anhnguyen.bookcloud.domain.Book;
import com.anhnguyen.bookcloud.mapper.BookMapper;
import com.anhnguyen.bookcloud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController implements BooksApi {
    @Autowired
    private BookService bookService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<BookResponse>> getBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(BookMapper.mapList(books));
    }
}
