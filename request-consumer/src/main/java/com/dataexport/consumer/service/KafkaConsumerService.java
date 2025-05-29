package com.dataexport.consumer.service;

import com.dataexport.consumer.record.RequestRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(
            topics = "request-transaction-topic",
            groupId = "group1",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(RequestRecord requestRecord) {
        log.info("Consumed Kafka event -> {}", requestRecord);
        // process your requestRecord here
    }
}
