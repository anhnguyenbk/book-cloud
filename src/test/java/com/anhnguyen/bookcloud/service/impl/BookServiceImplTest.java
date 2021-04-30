package com.anhnguyen.bookcloud.service.impl;


import com.anhnguyen.bookcloud.api.model.BookRequest;
import com.anhnguyen.bookcloud.domain.Author;
import com.anhnguyen.bookcloud.domain.Book;
import com.anhnguyen.bookcloud.domain.BookType;
import com.anhnguyen.bookcloud.model.BookSearchCriteria;
import com.anhnguyen.bookcloud.repository.AuthorRepository;
import com.anhnguyen.bookcloud.repository.BookRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BookServiceImplTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @After
    public void tearDown() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    public void getBooks() {
        Book firstBook = new Book();
        firstBook.setTitle("Life of Pi");
        firstBook.setType(BookType.FICTION);
        firstBook.setPublishDate(LocalDate.of(2001, 9, 11));
        firstBook.setCreatedAt(LocalDateTime.now());
        firstBook.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(firstBook);

        Book secondBook = new Book();
        secondBook.setTitle("Beatrice and Virgil");
        secondBook.setType(BookType.FICTION);
        secondBook.setPublishDate(LocalDate.of(2010, 4, 6));
        secondBook.setCreatedAt(LocalDateTime.now());
        secondBook.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(secondBook);

        List<Book> books = bookServiceImpl.getBooks();
        assertThat(books.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void getById() {
        Author author = new Author();
        author.setName("Yann Martel");
        author.setBirthDay(LocalDate.of(1963, 6, 25));
        author.setCreatedAt(LocalDateTime.now());
        author.setUpdatedAt(LocalDateTime.now());
        Author savedAuthor = authorRepository.save(author);

        Book book = new Book();
        book.setTitle("Life of Pi");
        book.setType(BookType.FICTION);
        book.setPublishDate(LocalDate.of(2001, 9, 11));
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());
        book.setAuthors(List.of(savedAuthor));
        Book savedBook = bookRepository.save(book);

        Book returnBook = bookServiceImpl.getById(savedBook.getId());
        assertThat(returnBook.getTitle()).isEqualTo("Life of Pi");
        assertThat(returnBook.getType()).isEqualTo(BookType.FICTION);
        assertThat(returnBook.getAuthors().get(0).getName()).isEqualTo("Yann Martel");
    }

    @Test
    @Transactional
    public void create() {
        Author author = new Author();
        author.setName("Yann Martel");
        author.setBirthDay(LocalDate.of(1963, 6, 25));
        author.setCreatedAt(LocalDateTime.now());
        author.setUpdatedAt(LocalDateTime.now());
        Author savedAuthor = authorRepository.save(author);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("Life of Pi");
        bookRequest.setType("FICTION");
        bookRequest.setPublishDate(LocalDate.of(2001, 9, 11));
        bookRequest.setAuthorIds(List.of(savedAuthor.getId()));

        Book returnBook = bookServiceImpl.create(bookRequest);
        assertThat(returnBook.getTitle()).isEqualTo("Life of Pi");
        assertThat(returnBook.getType()).isEqualTo(BookType.FICTION);
        assertThat(returnBook.getAuthors().get(0).getName()).isEqualTo("Yann Martel");
    }

    @Test
    @Transactional
    public void update() {
        Author author = new Author();
        author.setName("Yann Martel");
        author.setBirthDay(LocalDate.of(1963, 6, 25));
        author.setCreatedAt(LocalDateTime.now());
        author.setUpdatedAt(LocalDateTime.now());
        Author savedAuthor = authorRepository.save(author);

        Book book = new Book();
        book.setTitle("Life of Pi");
        book.setType(BookType.FICTION);
        book.setPublishDate(LocalDate.of(2001, 9, 11));
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());
        book.setAuthors(List.of(savedAuthor));
        Book savedBook = bookRepository.save(book);

        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("New Life of Pi");
        bookRequest.setType("FICTION");
        bookRequest.setPublishDate(LocalDate.of(2001, 9, 11));
        bookRequest.setAuthorIds(List.of(savedAuthor.getId()));

        Book returnBook = bookServiceImpl.update(savedBook.getId(), bookRequest);
        assertThat(returnBook.getTitle()).isEqualTo("New Life of Pi");
        assertThat(returnBook.getType()).isEqualTo(BookType.FICTION);
        assertThat(returnBook.getAuthors().get(0).getName()).isEqualTo("Yann Martel");
    }

    @Test
    @Transactional
    public void delete() {
        Author author = new Author();
        author.setName("Yann Martel");
        author.setBirthDay(LocalDate.of(1963, 6, 25));
        author.setCreatedAt(LocalDateTime.now());
        author.setUpdatedAt(LocalDateTime.now());
        Author savedAuthor = authorRepository.save(author);

        Book book = new Book();
        book.setTitle("Life of Pi");
        book.setType(BookType.FICTION);
        book.setPublishDate(LocalDate.of(2001, 9, 11));
        book.setCreatedAt(LocalDateTime.now());
        book.setUpdatedAt(LocalDateTime.now());
        book.setAuthors(List.of(savedAuthor));
        Book savedBook = bookRepository.save(book);

        assertThat(bookRepository.findAll().size()).isEqualTo(1);
        bookServiceImpl.delete(savedBook.getId());
        assertThat(bookRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    @Transactional
    public void search() {
        Author author = new Author();
        author.setName("Yann Martel");
        author.setBirthDay(LocalDate.of(1963, 6, 25));
        author.setCreatedAt(LocalDateTime.now());
        author.setUpdatedAt(LocalDateTime.now());
        Author savedAuthor = authorRepository.save(author);

        Book firstBook = new Book();
        firstBook.setTitle("Life of Pi");
        firstBook.setType(BookType.FICTION);
        firstBook.setPublishDate(LocalDate.of(2001, 9, 11));
        firstBook.setCreatedAt(LocalDateTime.now());
        firstBook.setUpdatedAt(LocalDateTime.now());
        firstBook.setAuthors(List.of(savedAuthor));
        bookRepository.save(firstBook);

        Book secondBook = new Book();
        secondBook.setTitle("Beatrice and Virgil");
        secondBook.setType(BookType.FICTION);
        secondBook.setPublishDate(LocalDate.of(2010, 4, 6));
        secondBook.setCreatedAt(LocalDateTime.now());
        secondBook.setUpdatedAt(LocalDateTime.now());
        secondBook.setAuthors(List.of(savedAuthor));
        bookRepository.save(secondBook);

        BookSearchCriteria criteria = new BookSearchCriteria();
        criteria.setAuthor("Yann");
        criteria.setTitle("Pi");

        List<Book> result = bookServiceImpl.search(criteria);
        assertThat(result.size()).isEqualTo(1);
    }
}