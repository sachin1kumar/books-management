package com.books.management.booksmanagement.controllers;

import com.books.management.booksmanagement.entities.PremiumBooks;
import com.books.management.booksmanagement.services.PremiumBookNotFoundException;
import com.books.management.booksmanagement.services.PremiumBooksService;
import com.books.management.booksmanagement.services.RewardsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/premiumbooks")
public class PremiumBooksController {

    @Autowired
    private PremiumBooksService premiumBooksService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RewardsService rewardsService;

    @PostMapping("/books")
    public PremiumBooks addPremiumBook(@RequestBody PremiumBooks premiumBooks) {
        if (premiumBooks.getRewardsList() != null && premiumBooks.getRewardsList().size() > 0){
            //persisting data in Rewards collection.
            rewardsService.createRewards(premiumBooks.getRewardsList());
        }
        return premiumBooksService.addPremiumBook(premiumBooks);
    }

    @GetMapping("/books/{id}")
    public Optional<PremiumBooks> getBook(@PathVariable String id) {
        return premiumBooksService.getPremiumBook(id);
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<PremiumBooks> updateBook(@PathVariable String id, @RequestBody JsonPatch patch) {
        try {
            Optional<PremiumBooks> premiumBook = premiumBooksService.getPremiumBook(id);
            PremiumBooks bookPatched = applyPatchToCustomer(patch, premiumBook);
            premiumBooksService.updatePremiumBook(bookPatched);
            return ResponseEntity.ok(bookPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (PremiumBookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/books")
    public List<PremiumBooks> getAllPremiumBooks() {
        return premiumBooksService.getAllPremiumBooks();
    }

    private PremiumBooks applyPatchToCustomer(JsonPatch patch, Optional<PremiumBooks> targetBook)
            throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetBook, JsonNode.class));
        return objectMapper.treeToValue(patched, PremiumBooks.class);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable String id) {
        try {
            premiumBooksService.deletePremiumBook(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new PremiumBookNotFoundException("Book not found");
        }
    }

    @GetMapping("/booksByName/{bookName}")
    public List<PremiumBooks> getBooksByName(@PathVariable String bookName) {
        return premiumBooksService.getPremiumBooksByName(bookName);
    }

    @GetMapping("/booksByNameAndAuthor")
    public List<PremiumBooks> getBooksByNameAndAuthor(@RequestParam String bookName, @RequestParam String authorName) {
        return premiumBooksService.getPremiumBooksByNameAndAuthor(bookName, authorName);
    }

    @GetMapping("/booksByNameOrAuthor")
    public List<PremiumBooks> getBooksByNameOrAuthor(@RequestParam String bookName, @RequestParam String authorName) {
        return premiumBooksService.getPremiumBooksByNameOrAuthor(bookName, authorName);
    }

    @GetMapping("/booksByPagination")
    public List<PremiumBooks> getBooksByPagination(@RequestParam int pageNo, @RequestParam int pageSize) {
        return premiumBooksService.getPremiumBooksByPagination(pageNo, pageSize);
    }

    @GetMapping("/booksBySorting")
    public List<PremiumBooks> getBooksBySorting() {
        return premiumBooksService.getPremiumBooksByOrder();
    }

    @GetMapping("/booksByRewardName")
    public List<PremiumBooks> getBooksByRewardName(@RequestParam String rewardName) {
        return premiumBooksService.getPremiumBooksByRewardName(rewardName);
    }

    @GetMapping("/booksByBookNameIsLike")
    public List<PremiumBooks> getBooksByBookNameIsLike(@RequestParam String bookName) {
        return premiumBooksService.getPremiumBooksByBookNameIsLike(bookName);
    }

    @GetMapping("/booksByAuthorNameStartsWith")
    public List<PremiumBooks> getBooksByAuthorNameStartsWith(@RequestParam String authorName) {
        return premiumBooksService.getPremiumBooksByAuthorNameStartsWith(authorName);
    }

    @GetMapping("/booksByRewardsListRewardId")
    public List<PremiumBooks> getPremiumBooksByRewardsListRewardId(@RequestParam String rewardId) {
        return premiumBooksService.getPremiumBooksByRewardsListRewardId(rewardId);
    }
}
