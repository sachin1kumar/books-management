package com.books.management.booksmanagement.consumers;

import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.services.ShortlistedService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortlistedBookConsumer {

    @Autowired
    private ShortlistedService shortlistedService;
    private final Logger logger = LoggerFactory.getLogger(ShortlistedBookConsumer.class);

    @RabbitListener(queues = "${spring.rabbitmq.addShortListedQueue}")
    public void consumeShortlistedDetails(final ShortlistDetails shortlistDetails) throws JsonProcessingException {
        logger.info("Message received from queue for consumeShortlistedDetails: {}"
                , new ObjectMapper().writeValueAsString(shortlistDetails));
        shortlistedService.addToShortlist(shortlistDetails);
    }

    @RabbitListener(queues = "${spring.rabbitmq.removeShortListedQueue}")
    public void removeShortlistedBook(final Long shortListedId) throws JsonProcessingException {
        logger.info("Message received from queue for removeShortlistedBook: {}"
                , new ObjectMapper().writeValueAsString(shortListedId));
        shortlistedService.removeFromShortlist(shortListedId);
    }
}
