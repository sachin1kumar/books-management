package com.books.management.booksmanagement.repositories;

import com.books.management.booksmanagement.entities.PremiumBooks;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PremiumBooksRepository extends MongoRepository<PremiumBooks, String> {

    List<PremiumBooks> findByBookName(String name);
    List<PremiumBooks> findByBookNameAndAuthorName(String bookName, String authorName);
}
