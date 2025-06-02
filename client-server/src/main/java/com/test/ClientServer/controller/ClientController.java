package com.test.ClientServer.controller;

import com.test.ClientServer.model.ResponseRecord;
import com.test.ClientServer.service.ExportResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client/export")
public class ClientController {

    private final ExportResponseService exportService;

    public ClientController(ExportResponseService exportService) {
        this.exportService = exportService;
    }

    @GetMapping("/status")
    public List<ResponseRecord> getSuccessRecords() {
        return exportService.getSuccessRecords();
    }

    @PostMapping("/mark-downloaded/{exportId}")
    public ResponseEntity<?> markAsDownloaded(@PathVariable Integer exportId) {
        exportService.markAsDownloaded(exportId);
        return ResponseEntity.ok().build();
    }
}

