package com.dataexport.TransactionServer.service.kafka;

import com.dataexport.TransactionServer.model.RequestRecord;
import com.dataexport.TransactionServer.service.ExportProcessorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final Logger log = LoggerFactory.getLogger(ConsumerService.class);
    private final ExportProcessorService exportProcessorService;

    @KafkaListener(
            topics = "request-transaction-topic",
            groupId = "group1",
            containerFactory = "recordListener"
    )
    public void consume(RequestRecord record) {
        log.info("Incoming Record = {}", record);

        // Pass the record to DecisionService
        exportProcessorService.processRecord(record);
    }
}
