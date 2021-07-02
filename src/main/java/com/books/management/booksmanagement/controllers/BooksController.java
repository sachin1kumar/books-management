package com.books.management.booksmanagement.controllers;

import com.books.management.booksmanagement.entities.Books;
import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.services.BooksService;
import com.books.management.booksmanagement.services.ShortlistedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BooksController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private ShortlistedService shortlistedService;

    @GetMapping("/getBooks")
    public List<Books> getAllBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/getBook/{bookId}")
    public Optional<Books> getBookById(@PathVariable final Long bookId) {
        return booksService.getBookById(bookId);
    }

    @GetMapping("/getShortlistedBooks/{userId}")
    public List<Books> getShortlistedBooks(@PathVariable final Long userId) {
        final List<ShortlistDetails> shortlistDetails = shortlistedService.getAllShortListedDetailsById(userId);
        return booksService.getShortlistedBooksForUser(shortlistDetails);
    }
}
