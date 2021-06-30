package com.books.management.booksmanagement.services;

import com.books.management.booksmanagement.entities.Books;
import com.books.management.booksmanagement.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    public Optional<Books> getBookById(Long bookId) {
        return booksRepository.findById(bookId);
    }
}
