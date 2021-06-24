package com.books.management.booksmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "shortlisted_books")
public final class ShortlistedBook {

    @Id
    @Column(name = "shortlisted_id")
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "book_id")
    private long bookId;

    public ShortlistedBook() {

    }

    public ShortlistedBook(long id, long userId, long bookId) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
    }

    public long getId() {
        return id;
    }

    public long getBookId() {
        return bookId;
    }

    public long getUserId() {
        return userId;
    }

}
