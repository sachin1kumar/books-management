package com.books.management.booksmanagement.services;

import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.repositories.ShortlistedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortlistedService {

    @Autowired
    private ShortlistedRepository shortlistedRepository;

    public void addToShortlist(ShortlistDetails shortlistDetails) {
        final Long userId = shortlistDetails.getUserId();
        final Long bookId = shortlistDetails.getBookId();
        final ShortlistDetails saveShortlistDetails = new ShortlistDetails(userId, bookId);
        shortlistedRepository.save(saveShortlistDetails);
    }
}
