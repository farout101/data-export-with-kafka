package com.dataexport.consumer.service;

import com.dataexport.consumer.model.RequestRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final DecisionService decisionService;

    public KafkaConsumerService(DecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @KafkaListener(
            topics = "request-transaction-topic",
            groupId = "group1",
            containerFactory = "recordListener"
    )
    public void consume(RequestRecord record) {
        log.info("Incoming Record = {}", record);

        // Pass the record to DecisionService
        decisionService.processRecord(record);
    }
}
