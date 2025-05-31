package com.test.ClientServer.service;

import com.test.ClientServer.model.ResponseRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class ExportResponseService {

    private final List<ResponseRecord> responseList = new CopyOnWriteArrayList<>();

    public void addRecord(ResponseRecord record) {
        if ("SUCCESS".equals(record.getStatus())) {
            responseList.add(record);
        }
    }

    public List<ResponseRecord> getSuccessRecords() {
        return responseList.stream()
                .filter(r -> "SUCCESS".equals(r.getStatus()))
                .collect(Collectors.toList());
    }

    public void markAsDownloaded(Long exportId) {
        responseList.stream()
                .filter(r -> r.getExportId().equals(exportId))
                .findFirst()
                .ifPresent(r -> r.setStatus("DOWNLOADED"));
    }
}
