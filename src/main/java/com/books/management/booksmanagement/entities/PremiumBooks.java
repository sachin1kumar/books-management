package com.books.management.booksmanagement.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@ToString

@Document(collection = "bookDetails")
public class PremiumBooks {

    @Id
    private String bookId;
    private String bookName;
    private String authorName;
    private List<Rewards> rewardsList;
}
