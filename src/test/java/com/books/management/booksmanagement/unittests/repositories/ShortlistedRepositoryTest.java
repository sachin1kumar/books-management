package com.books.management.booksmanagement.unittests.repositories;

import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.repositories.ShortlistedRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShortlistedRepositoryTest {

    @Autowired
    private ShortlistedRepository shortlistedRepository;

    @Test
    public void shouldReturnAllDataFromDb() {
        //Given
        ShortlistDetails firstShortlistedDetails = new ShortlistDetails(1234900L, 123L, 1234L);
        ShortlistDetails secondShortlistedDetails = new ShortlistDetails(1234901L, 123L, 1235L);
        shortlistedRepository.save(firstShortlistedDetails);
        shortlistedRepository.save(secondShortlistedDetails);
        //When
        List<ShortlistDetails> shortlistDetailsList = shortlistedRepository.findAll();
        //Then
        assertEquals(2, shortlistDetailsList.size());
    }
}
