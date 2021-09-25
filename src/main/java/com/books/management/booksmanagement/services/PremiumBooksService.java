package com.books.management.booksmanagement.services;

import com.books.management.booksmanagement.entities.PremiumBooks;
import com.books.management.booksmanagement.repositories.PremiumBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PremiumBooksService {

    @Autowired
    private PremiumBooksRepository premiumBooksRepository;

    public Optional<PremiumBooks> getPremiumBook(String id) {
        return premiumBooksRepository.findById(id);
    }

    public PremiumBooks addPremiumBook(PremiumBooks premiumBooks) {
        return premiumBooksRepository.save(premiumBooks);
    }

    public PremiumBooks updatePremiumBook(PremiumBooks premiumBooks) {
        return premiumBooksRepository.save(premiumBooks);
    }

    public List<PremiumBooks> getAllPremiumBooks() {
        return premiumBooksRepository.findAll();
    }

    public void deletePremiumBook(String id) {
        premiumBooksRepository.deleteById(id);
    }

    public List<PremiumBooks> getPremiumBooksByName(String name) {
        return premiumBooksRepository.findByBookName(name);
    }

    public List<PremiumBooks> getPremiumBooksByNameAndAuthor(String bookName, String authorName) {
        return premiumBooksRepository.findByBookNameAndAuthorName(bookName, authorName);
    }

    public List<PremiumBooks> getPremiumBooksByNameOrAuthor(String bookName, String authorName) {
        return premiumBooksRepository.findByBookNameOrAuthorName(bookName, authorName);
    }

    public List<PremiumBooks> getPremiumBooksByPagination(int pageNo, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return premiumBooksRepository.findAll(pageable).getContent();
    }

    public List<PremiumBooks> getPremiumBooksByOrder() {
        final Sort sort = Sort.by(Sort.Direction.ASC, "bookName");
        return premiumBooksRepository.findAll(sort);
    }

    public List<PremiumBooks> getPremiumBooksByRewardName(String rewardName) {
        return premiumBooksRepository.findByRewardsListRewardName(rewardName);
    }

    public List<PremiumBooks> getPremiumBooksByBookNameIsLike(String bookName) {
        return premiumBooksRepository.findByBookNameIsLike(bookName);
    }
}
