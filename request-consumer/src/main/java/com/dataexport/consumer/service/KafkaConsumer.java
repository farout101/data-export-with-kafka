package com.dataexport.consumer.service;

import com.dataexport.consumer.model.RequestRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "request-transaction-topic",
            groupId = "group1",
            containerFactory = "recordListener"
    )
    public void consume(RequestRecord record)
    {
        // Print statement
        System.out.println("message = " + record);
    }
}