package com.test.requestproducer.service;

import com.test.requestproducer.model.RequestRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, RequestRecord> kafkaTemplate;

    private static final String TOPIC = "request-transaction-topic";

    public void sendRequest(RequestRecord request) {
        kafkaTemplate.send(TOPIC, request);
    }
}
