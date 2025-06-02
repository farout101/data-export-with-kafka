package com.test.ClientServer.service;

import com.test.ClientServer.model.ResponseRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class ExportResponseService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Store records + their scheduled removal tasks
    private final ConcurrentHashMap<ResponseRecord, ScheduledFuture<?>> responseList = new ConcurrentHashMap<>();

    public void addRecord(ResponseRecord record) {
        // Add the record and schedule removal after 15 minutes
        ScheduledFuture<?> future = scheduler.schedule(() -> {
            responseList.remove(record);
            System.out.println("Removed record after 15 minutes: " + record);
        }, 15, TimeUnit.MINUTES);

        // Put record and scheduled future in map
        responseList.put(record, future);
    }

    public List<ResponseRecord> getSuccessRecords() {
        return responseList.keySet().stream()
                .filter(r -> "SUCCESS".equals(r.getStatus()))
                .collect(Collectors.toList());
    }

    public void markAsDownloaded(Integer exportId) {
        responseList.keySet().stream()
                .filter(r -> exportId.equals(r.getExportId()))
                .findFirst()
                .ifPresent(r -> r.setStatus("DOWNLOADED"));
    }
}
