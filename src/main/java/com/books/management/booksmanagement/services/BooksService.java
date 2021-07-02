package com.books.management.booksmanagement.services;

import com.books.management.booksmanagement.entities.Books;
import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    public Optional<Books> getBookById(final Long bookId) {
        return booksRepository.findById(bookId);
    }

    public List<Books> getShortlistedBooksForUser(final List<ShortlistDetails> shortlistDetails) {
        final List<Books> booksList = new ArrayList<>();
        shortlistDetails
                .forEach(bookId ->
                        booksList.add(booksRepository.findById(bookId.getBookId()).stream().iterator().next()));
        return booksList;
    }
}
