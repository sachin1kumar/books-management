package com.books.management.booksmanagement.controllers;

import com.books.management.booksmanagement.entities.AuthorizationResponse;
import com.books.management.booksmanagement.entities.Books;
import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.services.AuthorizationProxy;
import com.books.management.booksmanagement.services.BooksService;
import com.books.management.booksmanagement.services.ShortlistedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BooksController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private ShortlistedService shortlistedService;

    @Autowired
    private AuthorizationProxy authorizationProxy;

    private Logger logger = LoggerFactory.getLogger(BooksController.class);

    @GetMapping("/getBooks")
    public List<Books> getAllBooks(@RequestHeader("Authorization") String bearerToken) {
        final AuthorizationResponse authorizationResponse = authorizationProxy.authorizeUser(bearerToken);
        logger.error("AuthorizationResponse: "+authorizationResponse.getRoleType());
        final String roleType = authorizationResponse.getRoleType();
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
