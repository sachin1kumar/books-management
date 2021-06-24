package com.books.management.booksmanagement.services;

import com.books.management.booksmanagement.entities.ShortlistedBook;
import com.books.management.booksmanagement.repositories.ShortlistedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortlistedService {

    @Autowired
    private ShortlistedRepository shortlistedRepository;

    void addToShortlist(ShortlistedBook shortlistedBook) {
        shortlistedRepository.save(shortlistedBook);
    }
}
