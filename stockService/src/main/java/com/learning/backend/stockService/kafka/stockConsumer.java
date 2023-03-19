package com.learning.backend.stockService.kafka;

import com.learning.backend.basedomains.DTO.OrderEvent;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@EnableKafka
@Component
public class stockConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(stockConsumer.class);
    @KafkaListener(topics = "${spring.kafka.topic.name}" , groupId = "${spring.kafka.consumer.group_id}")
    public void consumeMessages(OrderEvent orderevent) {

        System.out.println(orderevent);

    }
}
