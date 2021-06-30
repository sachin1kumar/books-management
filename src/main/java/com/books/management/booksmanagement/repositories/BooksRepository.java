package com.books.management.booksmanagement.repositories;

import com.books.management.booksmanagement.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
