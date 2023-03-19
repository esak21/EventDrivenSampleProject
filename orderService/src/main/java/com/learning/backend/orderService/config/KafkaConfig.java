package com.learning.backend.orderService.config;

import com.learning.backend.basedomains.DTO.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapservers;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keyserializertype;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueserializertype;

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name(topicName).build();
    }

    @Bean
    public  ProducerFactory<String, OrderEvent> Orderproducerconfig () {

        Map<String, Object> conf = new HashMap<>();
        conf.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapservers);
        conf.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keyserializertype);
        conf.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueserializertype);

        return new DefaultKafkaProducerFactory<>(conf);

    }

    @Bean
    public KafkaTemplate kafaktemplatemethod () {


        return new KafkaTemplate(Orderproducerconfig());
    }
}
