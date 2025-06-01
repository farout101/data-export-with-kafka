package com.dataexport.TransactionServer.service;

import com.dataexport.TransactionServer.enu.FileFormat;
import com.dataexport.TransactionServer.model.ResponseRecord;
import org.springframework.stereotype.Service;

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
