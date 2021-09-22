package com.books.management.booksmanagement.services;

import com.books.management.booksmanagement.entities.PremiumBooks;
import com.books.management.booksmanagement.repositories.PremiumBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PremiumBooksService {

    @Autowired
    private PremiumBooksRepository premiumBooksRepository;

    public Optional<PremiumBooks> getPremiumBook(int id) {
        return premiumBooksRepository.findById(id);
    }

    public PremiumBooks addPremiumBook(PremiumBooks premiumBooks) {
        return premiumBooksRepository.save(premiumBooks);
    }

    public PremiumBooks updatePremiumBook(PremiumBooks premiumBooks) {
        return premiumBooksRepository.save(premiumBooks);
    }

    public void deletePremiumBook(int id){
        premiumBooksRepository.deleteById(id);
    }
}
