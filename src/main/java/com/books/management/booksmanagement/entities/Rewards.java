package com.books.management.booksmanagement.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class Rewards {

    @Id
    private String rewardId;
    private String rewardName;
}
