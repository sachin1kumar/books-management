package com.books.management.booksmanagement.services;

import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.repositories.ShortlistedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShortlistedService {

    @Autowired
    private ShortlistedRepository shortlistedRepository;

    public void addToShortlist(final ShortlistDetails shortlistDetails) {
        final Long userId = shortlistDetails.getUserId();
        final Long bookId = shortlistDetails.getBookId();
        final ShortlistDetails saveShortlistDetails = new ShortlistDetails(userId, bookId);
        shortlistedRepository.save(saveShortlistDetails);
    }

    public void removeFromShortlist(final Long shortlistedId) {
        shortlistedRepository.deleteById(shortlistedId);
    }

    public List<ShortlistDetails> getAllShortListedDetailsById(final Long userId) {
        final List<ShortlistDetails> shortlistedList = shortlistedRepository.findAll();
        return shortlistedList
                .stream()
                .filter(currentUserId -> userId == currentUserId.getUserId())
                .collect(Collectors.toList());
    }
}
