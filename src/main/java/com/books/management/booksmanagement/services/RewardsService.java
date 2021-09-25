package com.books.management.booksmanagement.services;

import com.books.management.booksmanagement.entities.Rewards;
import com.books.management.booksmanagement.repositories.RewardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardsService {

    @Autowired
    private RewardsRepository rewardsRepository;

    public List<Rewards> createRewards(List<Rewards> rewards) {
        return rewardsRepository.saveAll(rewards);
    }
}
