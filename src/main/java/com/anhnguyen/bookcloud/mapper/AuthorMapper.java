package com.anhnguyen.bookcloud.mapper;

import com.anhnguyen.bookcloud.api.model.AuthorResponse;
import com.anhnguyen.bookcloud.domain.Author;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AuthorMapper is responsible for converting from Author entity (service layer) to AuthorResponse (view layer)
 */
public class AuthorMapper {
    /**
     * Convert a List of Author entity to List of AuthorResponse
     * @param authors
     * @return
     */
    public static List<AuthorResponse> mapList(List<Author> authors) {
        return authors.stream().map(author -> map(author)).collect(Collectors.toList());
    }

    /**
     * Convert single Author entity to single AuthorResponse
     * @param author
     * @return
     */
    public static AuthorResponse map(Author author) {
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());
        authorResponse.setBirthDay(author.getBirthDay());
        return authorResponse;
    }
}
