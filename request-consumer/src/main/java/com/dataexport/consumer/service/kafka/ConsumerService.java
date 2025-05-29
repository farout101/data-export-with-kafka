package com.dataexport.consumer.service.kafka;

import com.dataexport.consumer.model.RequestRecord;
import com.dataexport.consumer.service.StorageProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final Logger log = LoggerFactory.getLogger(ConsumerService.class);
    private final StorageProcessorService storageProcessorService;

    public ConsumerService(StorageProcessorService storageProcessorService) {
        this.storageProcessorService = storageProcessorService;
    }

    @KafkaListener(
            topics = "request-transaction-topic",
            groupId = "group1",
            containerFactory = "recordListener"
    )
    public void consume(RequestRecord record) {
        log.info("Incoming Record = {}", record);

        // Pass the record to DecisionService
        storageProcessorService.processRecord(record);
    }
}
