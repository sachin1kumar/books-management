package com.books.management.booksmanagement.unittests.services;

import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.repositories.ShortlistedRepository;
import com.books.management.booksmanagement.services.ShortlistedService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShortlistedServiceTest {

    @InjectMocks
    private ShortlistedService shortlistedService;

    @Mock
    private ShortlistedRepository shortlistedRepository;

    @Test
    public void addToShortlistTest() {
        ShortlistDetails firstShortlistedDetails = new ShortlistDetails(1234900L, 123L, 1234L);
        shortlistedRepository.save(firstShortlistedDetails);
        ArgumentCaptor<ShortlistDetails> captor = ArgumentCaptor.forClass(ShortlistDetails.class);
        verify(shortlistedRepository, times(1)).save(captor.capture());
        ShortlistDetails shortlistDetail = captor.getValue();
        Long expectedShortId = 1234900L;
        Long actualShortId = shortlistDetail.getShortlistedId();
        assertEquals(expectedShortId, actualShortId);
    }

    @Test
    public void removeFromShortlistTest() {
        ShortlistDetails firstShortlistedDetails = new ShortlistDetails(1234900L, 123L, 1234L);
        shortlistedRepository.deleteById(firstShortlistedDetails.getShortlistedId());
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(shortlistedRepository, times(1)).deleteById(captor.capture());
        Long actualShortId = captor.getValue();
        Long expectedShortId = 1234900L;
        assertEquals(expectedShortId, actualShortId);
    }
}
