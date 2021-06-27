package com.books.management.booksmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "shortlisted_books")
public final class ShortlistDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shortlisted_id")
    private Long shortlistedId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private Long bookId;

    public ShortlistDetails() {
    }

    public ShortlistDetails(Long shortlistedId, Long userId, Long bookId) {
        this.shortlistedId = shortlistedId;
        this.userId = userId;
        this.bookId = bookId;
    }

    public ShortlistDetails(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public long getBookId() {
        return bookId;
    }

    public long getUserId() {
        return userId;
    }

    public Long getShortlistedId() {
        return shortlistedId;
    }
}
