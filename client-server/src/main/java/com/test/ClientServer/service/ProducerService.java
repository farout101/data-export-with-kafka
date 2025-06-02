package com.test.ClientServer.service;

import com.test.ClientServer.model.RequestRecord;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.KafkaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class ProducerService {

    Logger log = LoggerFactory.getLogger(ProducerService.class);

    private final KafkaTemplate<String, RequestRecord> kafkaTemplate;

    private static final String TOPIC = "request-transaction-topic";

    @Async
    @Retryable(
            value = { KafkaException.class, TimeoutException.class },
            backoff = @Backoff(delay = 2000)
    )
    public void sendRequest(RequestRecord request) {
        CompletableFuture<SendResult<String, RequestRecord>> future = kafkaTemplate.send(TOPIC, request);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Message sent successfully for request: {}", request);
            } else {
                log.error("Failed to send message asynchronously for request: {}", request, ex);
                if (ex instanceof KafkaException || ex instanceof TimeoutException) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    @SuppressWarnings("unused")
    @Recover
    public void recover(KafkaException e, RequestRecord request) {
        log.error("KafkaException: Failed to send request after retries. Fallback logic triggered for: {}", request);
    }

    @SuppressWarnings("unused")
    @Recover
    public void recover(TimeoutException e, RequestRecord request) {
        log.error("TimeoutException: Kafka send timed out. Fallback logic triggered for: {}", request);
    }
}
