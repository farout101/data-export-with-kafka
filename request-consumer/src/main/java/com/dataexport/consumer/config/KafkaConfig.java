package com.dataexport.consumer.config;

// Importing required classes
import java.util.HashMap;
import java.util.Map;

import com.dataexport.consumer.model.RequestRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

// Annotation
@EnableKafka
@Configuration

// Class
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, RequestRecord> consumerFactory()
    {
        // Creating a map of string-object type
        Map<String, Object> config = new HashMap<>();
        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:39092,localhost:49092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        // Returning message in JSON format
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(RequestRecord.class));
    }

    // Creating a Listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequestRecord> recordListener()
    {
        ConcurrentKafkaListenerContainerFactory<String, RequestRecord> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}