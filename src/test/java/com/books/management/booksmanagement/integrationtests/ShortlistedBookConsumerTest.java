package com.books.management.booksmanagement.integrationtests;

import com.books.management.booksmanagement.BooksManagementApplication;
import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.repositories.ShortlistedRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = BooksManagementApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class ShortlistedBookConsumerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.addShortListedRoutingKey}")
    private String addShortListedRoutingKey;

    @Value("${spring.rabbitmq.removeShortListedRoutingKey}")
    private String removeShortListedRoutingKey;

    private final Logger logger = LoggerFactory.getLogger(ShortlistedBookConsumerTest.class);

    @Autowired
    private ShortlistedRepository shortlistedRepository;

    @Test
    public void shouldPersistShortlistedBook_WhenAddShortListedQueuePublished() throws JsonProcessingException,
            InterruptedException {
        //clearing the db before running this test..
        shortlistedRepository.deleteAll();
        final ShortlistDetails shortlistDetails = new ShortlistDetails(1L, 123L
                , 12345L);
        logger.info("Shortlisted request : {}", new ObjectMapper().writeValueAsString(shortlistDetails));
        rabbitTemplate.convertAndSend(exchange, addShortListedRoutingKey, shortlistDetails);
        Thread.sleep(1000);
        List<ShortlistDetails> booksList = shortlistedRepository.findAll();
        Thread.sleep(1000);
        assertTrue(booksList.size() != 0);
    }

    @Test
    public void shouldRemovePersistedShortlistedBook_WhenRemoveShortListedQueuePublished() throws JsonProcessingException,
            InterruptedException {
        final long shortListedId = 1L;
        logger.info("ShortlistedId request for remove: {}", new ObjectMapper().writeValueAsString(shortListedId));
        rabbitTemplate.convertAndSend(exchange, removeShortListedRoutingKey, shortListedId);
        Thread.sleep(1000);
        List<ShortlistDetails> booksList = shortlistedRepository.findAll();
        Thread.sleep(1000);
        assertEquals(0, booksList.size());
    }
}
