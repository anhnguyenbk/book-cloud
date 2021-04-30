package com.anhnguyen.bookcloud.controller;

import com.anhnguyen.bookcloud.api.BooksApi;
import com.anhnguyen.bookcloud.api.model.BookRequest;
import com.anhnguyen.bookcloud.api.model.BookResponse;
import com.anhnguyen.bookcloud.domain.Book;
import com.anhnguyen.bookcloud.mapper.BookMapper;
import com.anhnguyen.bookcloud.model.BookSearchCriteria;
import com.anhnguyen.bookcloud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RestController for handling /books request and response as JSON.
 * This controller is out of transaction scope. Be aware when accessing lazy properties.
 */
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

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> searchBook(@RequestParam(value = "title", required = false) String title,
                                                         @RequestParam(value = "author", required = false) String author) {
        BookSearchCriteria criteria = new BookSearchCriteria(title, author);
        List<Book> books = bookService.search(criteria);
        return ResponseEntity.ok(BookMapper.mapList(books));

    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest bookRequest) {
        Book newBook = bookService.create(bookRequest);
        return ResponseEntity.ok(BookMapper.map(newBook));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("bookId") Integer bookId) {
        Book book = bookService.getById(bookId);
        return ResponseEntity.ok(BookMapper.map(book));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable("bookId") Integer bookId,
                                                   @RequestBody BookRequest bookRequest) {
        Book book = bookService.update(bookId, bookRequest);
        return ResponseEntity.ok(BookMapper.map(book));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") Integer bookId) {
        bookService.delete(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
