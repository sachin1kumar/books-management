package com.books.management.booksmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "book_records")
public final class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title_id")
    private Long titleId;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "publisher_id")
    private Long publisherId;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    protected Books() {

    }
    public Books(Long bookId, Long titleId, Long authorId, Long publisherId, String description, Double price) {
        this.bookId = bookId;
        this.titleId = titleId;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.description = description;
        this.price = price;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getTitleId() {
        return titleId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}
