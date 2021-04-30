package com.anhnguyen.bookcloud.service.impl;

import com.anhnguyen.bookcloud.api.model.BookRequest;
import com.anhnguyen.bookcloud.domain.Author;
import com.anhnguyen.bookcloud.domain.Book;
import com.anhnguyen.bookcloud.domain.BookType;
import com.anhnguyen.bookcloud.exception.EntityNotFoundException;
import com.anhnguyen.bookcloud.model.BookSearchCriteria;
import com.anhnguyen.bookcloud.repository.AuthorRepository;
import com.anhnguyen.bookcloud.repository.BookRepository;
import com.anhnguyen.bookcloud.service.BookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation for BookService using JPA repositories to get and persist data.
 * Every method is wrapped in a transaction.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Integer bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found by Id: " + bookId));
    }

    @Override
    public Book create(BookRequest request) {
        List<Author> authors = getAuthors(request);

        Book newBook = new Book();
        newBook.setTitle(request.getTitle());
        newBook.setType(BookType.valueOf(request.getType()));
        newBook.setDescription(request.getDescription());
        newBook.setPublishDate(request.getPublishDate());
        newBook.setAuthors(authors);
        return bookRepository.save(newBook);
    }

    @Override
    public Book update(Integer bookId, BookRequest request) {
        List<Author> authors = getAuthors(request);

        Book book = getById(bookId);
        book.setTitle(request.getTitle());
        book.setType(BookType.valueOf(request.getType()));
        book.setDescription(request.getDescription());
        book.setPublishDate(request.getPublishDate());
        book.setAuthors(authors);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> search(BookSearchCriteria criteria) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        List<Predicate> predicateList = new ArrayList<>();
        if (StringUtils.isNotBlank(criteria.getTitle())) {
            Predicate titlePredicate = builder.like(root.get("title"), "%"+criteria.getTitle()+"%");
            predicateList.add(titlePredicate);
        }

        if (StringUtils.isNotBlank(criteria.getAuthor())) {
            Join join = root.join("authors", JoinType.LEFT);
            Predicate authorPredicate = builder.like(join.get("name"), "%" + criteria.getAuthor() + "%");
            predicateList.add(authorPredicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        return entityManager.createQuery(query.select(root).where(builder.and(predicates))).getResultList();
    }

    @Override
    public void delete(Integer bookId) {
        bookRepository.deleteById(bookId);
    }

    private List<Author> getAuthors(BookRequest request) {
        return authorRepository.findAllById(request.getAuthorIds());
    }
}
