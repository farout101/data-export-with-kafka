package com.test.ClientServer.service;

import com.test.ClientServer.model.ResponseRecord;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    private final ExportResponseService exportService;

    @KafkaListener(
            topics = "response-transaction-topic",
            groupId = "group1"
    )
    public void consume(ResponseRecord record) {
        log.info("Incoming Record = {}", record);

        exportService.addRecord(record);
    }
}