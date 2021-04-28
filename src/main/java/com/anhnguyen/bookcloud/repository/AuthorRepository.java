package com.anhnguyen.bookcloud.repository;

import com.anhnguyen.bookcloud.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
