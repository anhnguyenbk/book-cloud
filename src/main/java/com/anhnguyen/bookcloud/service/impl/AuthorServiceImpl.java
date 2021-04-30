package com.anhnguyen.bookcloud.service.impl;

import com.anhnguyen.bookcloud.api.model.AuthorRequest;
import com.anhnguyen.bookcloud.domain.Author;
import com.anhnguyen.bookcloud.domain.Book;
import com.anhnguyen.bookcloud.exception.BookExistingException;
import com.anhnguyen.bookcloud.exception.EntityNotFoundException;
import com.anhnguyen.bookcloud.repository.AuthorRepository;
import com.anhnguyen.bookcloud.repository.BookRepository;
import com.anhnguyen.bookcloud.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation for AuthorService using JPA repositories to get and persist data.
 * Every method is wrapped in a transaction.
 */
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Integer authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author not found by Id: " + authorId));
    }

    @Override
    public Author create(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setBirthDay(request.getBirthDay());
        return authorRepository.save(author);
    }

    @Override
    public void delete(Integer authorId) {
        Author author = getById(authorId);
        List<Book> authorBooks = bookRepository.findByAuthorsContaining(author);
        if (!authorBooks.isEmpty()) {
            throw new BookExistingException("Still has books references to author " + authorId);
        }
        authorRepository.deleteById(authorId);
    }

    @Override
    public Author update(Integer authorId, AuthorRequest request) {
        Author author = getById(authorId);
        author.setName(request.getName());
        author.setBirthDay(request.getBirthDay());
        return authorRepository.save(author);
    }
}
