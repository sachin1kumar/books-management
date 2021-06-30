package com.books.management.booksmanagement.controllers;

import com.books.management.booksmanagement.entities.Books;
import com.books.management.booksmanagement.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping("/getBooks")
    public List<Books> getAllBooks() {
        return booksService.getAllBooks();
    }

    @GetMapping("/getBook/{bookId}")
    public Optional<Books> getBookById(@PathVariable Long bookId) {
        return booksService.getBookById(bookId);
    }
}
