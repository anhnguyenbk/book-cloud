package com.anhnguyen.bookcloud.repository;

import com.anhnguyen.bookcloud.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
