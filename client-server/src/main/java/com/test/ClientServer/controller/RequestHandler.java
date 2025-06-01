package com.test.ClientServer.controller;

import com.test.ClientServer.model.RequestRecord;
import com.test.ClientServer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class RequestHandler {

    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private final ProducerService producerService;

    @PostMapping("")
    public void handleRequest(@RequestBody RequestRecord request) {
        producerService.sendRequest(request);
        log.info("Controller Called with RequestID : {}", request);
    }
}
