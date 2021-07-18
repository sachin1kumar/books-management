package com.books.management.booksmanagement.unittests.repositories;

import com.books.management.booksmanagement.entities.Books;
import com.books.management.booksmanagement.repositories.BooksRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BooksRepositoryTest {

    @Autowired
    private BooksRepository booksRepository;

    @Test
    public void shouldReturnAllDataFromDb() {
        //Given
        final Books firstBook = new Books(12345L, 1L, 1L, 1L, "How to be happy"
                ,123.23);
        final Books secondBook = new Books(12346L, 2L, 2L, 2L, "How to be sad"
                ,234.23);
        booksRepository.save(firstBook);
        booksRepository.save(secondBook);
        //When
        List<Books> listOfBooks = booksRepository.findAll();
        //Then
        assertEquals(2, listOfBooks.size());
    }

    @Test
    public void shouldReturnBook_WhenFindById() {
        //Given
        final Books firstBook = new Books(12345L, 1L, 1L, 1L, "How to be happy"
                ,123.23);
        booksRepository.save(firstBook);
        //When
        List<Books> listOfBooks = booksRepository.findAll();
        //Then
        assertEquals("How to be happy", listOfBooks.get(0).getDescription());
    }
}
