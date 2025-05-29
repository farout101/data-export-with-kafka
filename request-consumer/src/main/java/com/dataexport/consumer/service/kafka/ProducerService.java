package com.dataexport.consumer.service.kafka;

import com.dataexport.consumer.model.ResponseRecord;
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

    private final KafkaTemplate<String, ResponseRecord> kafkaTemplate;

    private static final String TOPIC = "response-transaction-topic";

    @Async
    @Retryable(
            value = { KafkaException.class, TimeoutException.class },
            backoff = @Backoff(delay = 2000)
    )
    public void sendRequest(ResponseRecord request) {
        CompletableFuture<SendResult<String, ResponseRecord>> future = kafkaTemplate.send(TOPIC, request);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Response sent successfully for request: {}", request);
            } else {
                log.error("Failed to send response asynchronously for request: {}", request, ex);
                if (ex instanceof KafkaException || ex instanceof TimeoutException) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    @Recover
    public void recover(KafkaException e, ResponseRecord request) {
        log.error("KafkaException: Failed to send response after retries. Fallback logic triggered for: {}", request);
    }

    @Recover
    public void recover(TimeoutException e, ResponseRecord request) {
        log.error("TimeoutException: Kafka send timed out. Fallback logic triggered for: {}", request);
    }
}