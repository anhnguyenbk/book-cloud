package com.anhnguyen.bookcloud.service;

import com.anhnguyen.bookcloud.api.model.AuthorRequest;
import com.anhnguyen.bookcloud.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();

    Author getById(Integer authorId);

    Author create(AuthorRequest request);

    void delete(Integer authorId);

    Author update(Integer authorId, AuthorRequest bookRequest);
}
