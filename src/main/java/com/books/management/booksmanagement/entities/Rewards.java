package com.books.management.booksmanagement.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "Rewards")
public class Rewards {

    @Id
    private String rewardId;
    private String rewardName;
}
