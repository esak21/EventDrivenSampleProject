package com.learning.backend.orderService.kafka;

import com.learning.backend.basedomains.DTO.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;



@Service
public class OrderProducer {

    public static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    @Autowired
    private NewTopic orderstopic;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkatemplate;



    public void sendMessages(OrderEvent orderevent){

        LOGGER.info(String.format("Order Event is ==> %s", orderevent));

        CompletableFuture<SendResult<String,OrderEvent>>  myfuture = kafkatemplate.send(topicName, orderevent);
        myfuture.whenComplete( (dresult, ex) -> {
            if (ex != null) {
                LOGGER.error("Execution failed", ex);
            } else {
                LOGGER.info("we get a result !!");
                LOGGER.info("Execution completed: {}", dresult);
                LOGGER.info("Execution completed: producerREcord {}", dresult.getProducerRecord());
                LOGGER.info("Execution completed: producerMetadata {}", dresult.getRecordMetadata());
                LOGGER.info("Execution completed: producerString{}", dresult.toString());

            }
        });


    }
}
