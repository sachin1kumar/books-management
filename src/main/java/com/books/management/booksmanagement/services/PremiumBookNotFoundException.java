package com.books.management.booksmanagement.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PremiumBookNotFoundException extends RuntimeException {

    public PremiumBookNotFoundException(String message) {
        super(message);
    }

}
