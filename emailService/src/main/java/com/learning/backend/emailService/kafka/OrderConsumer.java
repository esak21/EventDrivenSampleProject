package com.learning.backend.emailService.kafka;

import com.learning.backend.basedomains.DTO.OrderEvent;
import com.learning.backend.emailService.service.EmailSender;
import jakarta.mail.MessagingException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class OrderConsumer {

    @Autowired
    private EmailSender emailsender;
    public static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics =  "${spring.kafka.topic.name}" , groupId = "${spring.kafka.consumer.group_id}")
    public void consumer(OrderEvent orderevent) throws MessagingException {

        LOGGER.info(String.format("Order event received in Email --%s", orderevent));
        System.out.println(orderevent);

//        Send Email Based on the Order Event Details


        emailsender.sendEmail("esakkisankart@gmail.com", String.format(" We have received below Order %s", orderevent), String.format("Order received %s",orderevent.getOrders().getOrderId()) , "");

        LOGGER.info("Email Send Sucessfully ");
    }
}
