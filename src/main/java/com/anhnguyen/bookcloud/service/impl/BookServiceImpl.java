package com.anhnguyen.bookcloud.service.impl;

import com.anhnguyen.bookcloud.domain.Book;
import com.anhnguyen.bookcloud.repository.BookRepository;
import com.anhnguyen.bookcloud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
