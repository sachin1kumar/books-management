package com.books.management.booksmanagement.repositories;

import com.books.management.booksmanagement.entities.Rewards;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RewardsRepository extends MongoRepository<Rewards, String> {
}
