package com.dataexport.consumer.service;

import com.dataexport.consumer.enu.FileFormat;
import com.dataexport.consumer.enu.TransactionType;
import com.dataexport.consumer.model.ResponseRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PrepareResponse {

    public ResponseRecord prepare(Integer exportId, String fileName, FileFormat fileFormat, String status, String message, String downloadUrl) {
        return new ResponseRecord(
                exportId,
                fileName,
                fileFormat.name(),
                status,
                message,
                downloadUrl
        );
    }
}
