package com.anhnguyen.bookcloud.controller;

import com.anhnguyen.bookcloud.api.AuthorsApi;
import com.anhnguyen.bookcloud.api.model.AuthorRequest;
import com.anhnguyen.bookcloud.api.model.AuthorResponse;
import com.anhnguyen.bookcloud.domain.Author;
import com.anhnguyen.bookcloud.mapper.AuthorMapper;
import com.anhnguyen.bookcloud.service.AuthorService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RestController for handling /authors request and response as JSON.
 * This controller is out of transaction scope. Be aware when accessing lazy properties.
 */
@RestController
@RequestMapping("authors")
public class AuthorController implements AuthorsApi {
    @Autowired
    private AuthorService authorService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<AuthorResponse>> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        return ResponseEntity.ok(AuthorMapper.mapList(authors));
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorRequest authorRequest) {
        Author author = authorService.create(authorRequest);
        return ResponseEntity.ok(AuthorMapper.map(author));
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable("authorId") Integer authorId) {
        Author author = authorService.getById(authorId);
        return ResponseEntity.ok(AuthorMapper.map(author));
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable("authorId") Integer authorId,
                                                       @RequestBody AuthorRequest authorRequest) {
        Author author = authorService.update(authorId, authorRequest);
        return ResponseEntity.ok(AuthorMapper.map(author));
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("authorId") Integer authorId) {
        authorService.delete(authorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
