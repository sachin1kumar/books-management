package com.books.management.booksmanagement.repositories;

import com.books.management.booksmanagement.entities.ShortlistDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortlistedRepository extends JpaRepository<ShortlistDetails, Long> {
}
