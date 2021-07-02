package com.books.management.booksmanagement.consumers;

import com.books.management.booksmanagement.entities.ShortlistDetails;
import com.books.management.booksmanagement.services.ShortlistedService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortlistedBookConsumer {

    @Autowired
    private ShortlistedService shortlistedService;

    @RabbitListener(queues = "${spring.rabbitmq.addShortListedQueue}")
    public void consumeShortlistedDetails(final ShortlistDetails shortlistDetails) {
        System.out.println("Message receive from queue: " + shortlistDetails);
        shortlistedService.addToShortlist(shortlistDetails);
    }

    @RabbitListener(queues = "${spring.rabbitmq.removeShortListedQueue}")
    public void removeShortlistedBook(final Long shortListedId) {
        System.out.println("Message receive from queue: " + shortListedId);
        shortlistedService.removeFromShortlist(shortListedId);
    }
}
