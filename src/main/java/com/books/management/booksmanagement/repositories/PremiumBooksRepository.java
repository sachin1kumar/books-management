package com.books.management.booksmanagement.repositories;

import com.books.management.booksmanagement.entities.PremiumBooks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PremiumBooksRepository extends MongoRepository<PremiumBooks, Integer> {
}
