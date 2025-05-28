package com.test.requestproducer.controller;

import com.test.requestproducer.model.RequestRecord;
import com.test.requestproducer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class RequestHandler {

    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private final ProducerService producerService;

    @PostMapping("")
    public void handleRequest(@RequestBody RequestRecord request) {
        producerService.sendRequest(request);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        log.info(
                "Request received: export_id={}, file_format={}, transaction_type={}, start_datetime={}, end_datetime={}",
                request.export_id(),
                request.file_format(),
                request.transaction_type(),
                request.start_datetime().format(formatter),
                request.end_datetime().format(formatter)
        );
    }
}
