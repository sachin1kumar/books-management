package com.books.management.booksmanagement.unittests.services;

import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.repositories.ShortlistedRepository;
import com.books.management.booksmanagement.services.ShortlistedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    public void getAllShortListedDetailsByIdTest() {
        ShortlistDetails firstShortlistedDetails = new ShortlistDetails(1234900L, 123L, 1234L);
        ShortlistDetails secondShortlistedDetails = new ShortlistDetails(1234901L, 123L, 1235L);
        List<ShortlistDetails> shortlistDetailsList = new ArrayList<>();
        shortlistDetailsList.add(firstShortlistedDetails);
        shortlistDetailsList.add(secondShortlistedDetails);
        when(shortlistedRepository.findAll()).thenReturn(shortlistDetailsList);

        List<ShortlistDetails> actualShortlistedDetails = shortlistedService.getAllShortListedDetailsById(123L);
        Long actualBookId = actualShortlistedDetails.get(0).getBookId();
        Long expectedBookId = 1234L;
        int actualListSize = actualShortlistedDetails.size();
        assertEquals(expectedBookId, actualBookId);
        assertEquals(2, actualListSize);
    }
}
