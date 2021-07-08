package com.books.management.booksmanagement.unittests.services;

import com.books.management.booksmanagement.entities.Books;
import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.repositories.BooksRepository;
import com.books.management.booksmanagement.services.BooksService;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BooksServiceTest {

    @InjectMocks
    private BooksService booksService;

    @Mock
    private BooksRepository booksRepository;

    @Test
    public void getAllBooksFromRepository() {
        when(booksRepository.findAll()).thenReturn(createListOfBooks());
        List<Books> actualList = booksService.getAllBooks();
        assertEquals("Test Description1", actualList.get(0).getDescription());
        assertEquals("Test Description2", actualList.get(1).getDescription());
    }

    @Test
    public void getBookByIdTest() {
        when(booksRepository.findById(1234L)).thenReturn(createListOfBooks().stream().findFirst());
        Optional<Books> actualBook = booksService.getBookById(1234L);
        assertEquals("Test Description1", actualBook.get().getDescription());
        Double expectedPrice = 234.50;
        Double actualPrice = actualBook.get().getPrice();
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void getShortlistedBooksForUserTest() {
        when(booksRepository.findById(1234L)).thenReturn(Optional.ofNullable(createListOfBooks().get(0)));
        when(booksRepository.findById(1235L)).thenReturn(Optional.ofNullable(createListOfBooks().get(1)));
        List<Books> actualList = booksService.getShortlistedBooksForUser(createListOfShortlisted());
        Long expectedTitleId = 234L;
        Long actualTitleId = actualList.get(0).getTitleId();
        assertEquals(expectedTitleId, actualTitleId);
    }

    @NotNull
    private List<Books> createListOfBooks() {
        ArrayList<Books> listOfBooks = new ArrayList<>();
        Books firstBook = new Books(1234L, 234L, 567L, 789L, "Test Description1",
                234.50);
        Books secondBook = new Books(1235L, 235L, 568L, 790L, "Test Description2",
                234.51);
        listOfBooks.add(firstBook);
        listOfBooks.add(secondBook);
        return listOfBooks;
    }

    @NotNull
    private List<ShortlistDetails> createListOfShortlisted() {
        ArrayList<ShortlistDetails> listOfShortlisted = new ArrayList<>();
        ShortlistDetails firstShortlistedDetails = new ShortlistDetails(1234900L, 123L, 1234L);
        ShortlistDetails secondShortlistedDetails = new ShortlistDetails(123500L, 124L, 1235L);
        listOfShortlisted.add(firstShortlistedDetails);
        listOfShortlisted.add(secondShortlistedDetails);
        return listOfShortlisted;
    }
}
