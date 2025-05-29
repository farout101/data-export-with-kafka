package com.dataexport.consumer.config.service;

import com.dataexport.consumer.config.model.RecordEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    public static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);
    public static final String TOPIC = "request-transaction-topic";


    @KafkaListener(topics = TOPIC, groupId = "record-consumers")
    public void consumer(RecordEvent recordEvent) {
        LOGGER.info("Got Event -> {}", recordEvent);
    }
}
