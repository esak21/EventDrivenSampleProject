package com.learning.backend.emailService.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keydeserializertype;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valuedeserializertype;


    @Bean
    public ConsumerFactory<String, String> consumerfactory() {
        Map<String, Object> consumerconfig = new HashMap<>();
        consumerconfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerconfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "beginning");
        consumerconfig.put(ConsumerConfig.GROUP_ID_CONFIG, "cogesak_group_111");
        consumerconfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keydeserializertype);
        consumerconfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valuedeserializertype);

        return new DefaultKafkaConsumerFactory<>(consumerconfig);


    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory containerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerfactory());
        return factory;
    }

}
