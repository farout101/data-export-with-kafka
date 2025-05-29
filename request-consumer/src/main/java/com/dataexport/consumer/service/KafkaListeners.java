package com.dataexport.consumer.service;

import com.dataexport.consumer.record.RequestRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "request-transaction-topic", groupId = "group1")
    public void listen(RequestRecord request) {
        System.out.println("✅ Received request: " + request);
    }

}