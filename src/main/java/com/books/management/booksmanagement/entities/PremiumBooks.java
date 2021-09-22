package com.books.management.booksmanagement.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString

@Document(collection = "book")
public class PremiumBooks {

    @Id
    private int bookId;
    private String bookName;
    private String authorName;
}
