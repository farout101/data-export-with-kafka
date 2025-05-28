package com.test.requestproducer.controller;

import com.test.requestproducer.model.RequestRecord;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/request")
public class RequestHandler {

    @PostMapping("")
    public void  handleRequest(@RequestBody RequestRecord request) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.printf(
                "Request received: export_id=%d, file_format=%s, transaction_type=%s, start_datetime=%s, end_datetime=%s%n",
                request.export_id(),
                request.file_format(),
                request.transaction_type(),
                request.start_datetime().format(formatter),
                request.end_datetime().format(formatter)
        );
    }
}
