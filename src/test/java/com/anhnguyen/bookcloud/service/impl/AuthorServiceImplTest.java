package com.anhnguyen.bookcloud.service.impl;


import com.anhnguyen.bookcloud.api.model.AuthorRequest;
import com.anhnguyen.bookcloud.domain.Author;
import com.anhnguyen.bookcloud.repository.AuthorRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AuthorServiceImplTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorServiceImpl authorService;

    @After
    public void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    public void getAuthors() {
        Author firstAuthor = new Author();
        firstAuthor.setName("John Doe");
        firstAuthor.setBirthDay(LocalDate.of(1970, 5, 6));
        firstAuthor.setCreatedAt(LocalDateTime.now());
        firstAuthor.setUpdatedAt(LocalDateTime.now());
        authorRepository.save(firstAuthor);

        Author secondAuthor = new Author();
        secondAuthor.setName("Bob");
        secondAuthor.setBirthDay(LocalDate.of(1980, 12, 1));
        secondAuthor.setCreatedAt(LocalDateTime.now());
        secondAuthor.setUpdatedAt(LocalDateTime.now());
        authorRepository.save(secondAuthor);

        List<Author> authors = authorService.getAuthors();
        assertThat(authors.size()).isEqualTo(2);
    }

    @Test
    public void getById() {
        LocalDate birthDay = LocalDate.of(1994, 1, 1);

        Author author = new Author();
        author.setName("John Doe");
        author.setBirthDay(birthDay);
        author.setCreatedAt(LocalDateTime.now());
        author.setUpdatedAt(LocalDateTime.now());
        Author savedAuthor = authorRepository.save(author);

        Author responseAuthor = authorService.getById(savedAuthor.getId());
        assertThat(responseAuthor.getName()).isEqualTo("John Doe");
        assertThat(responseAuthor.getBirthDay()).isEqualTo(birthDay);
    }

    @Test
    public void create() {
        LocalDate birthDay = LocalDate.of(1994, 1, 1);

        AuthorRequest author = new AuthorRequest();
        author.setName("John Doe");
        author.setBirthDay(birthDay);
        Author savedAuthor = authorService.create(author);

        assertThat(savedAuthor.getName()).isEqualTo("John Doe");
        assertThat(savedAuthor.getBirthDay()).isEqualTo(birthDay);
        assertThat(savedAuthor.getCreatedAt()).isNotNull();
        assertThat(savedAuthor.getUpdatedAt()).isNotNull();
    }

    @Test
    public void delete() {
        LocalDate birthDay = LocalDate.of(1994, 1, 1);

        Author author = new Author();
        author.setName("John Doe");
        author.setBirthDay(birthDay);
        author.setCreatedAt(LocalDateTime.now());
        author.setUpdatedAt(LocalDateTime.now());
        Author savedAuthor = authorRepository.save(author);

        assertThat(authorRepository.findAll().size()).isEqualTo(1);
        authorService.delete(savedAuthor.getId());
        assertThat(authorRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    public void update() {
        LocalDate birthDay = LocalDate.of(1994, 1, 1);

        Author author = new Author();
        author.setName("John Doe");
        author.setBirthDay(birthDay);
        author.setCreatedAt(LocalDateTime.now());
        author.setUpdatedAt(LocalDateTime.now());
        Author savedAuthor = authorRepository.save(author);

        AuthorRequest request = new AuthorRequest();
        request.setName("New John Doe");
        request.setBirthDay(birthDay);
        Author resultAuthor = authorService.update(savedAuthor.getId(), request);

        assertThat(resultAuthor.getName()).isEqualTo("New John Doe");
        assertThat(savedAuthor.getBirthDay()).isEqualTo(birthDay);
    }
}