package com.dataexport.consumer.service;

import com.dataexport.consumer.enu.FileFormat;
import com.dataexport.consumer.enu.TransactionType;
import com.dataexport.consumer.model.RequestRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DecisionService {

    Logger log = LoggerFactory.getLogger(DecisionService.class);

    public void processRecord(RequestRecord record) {
        // Now you can access the file format, transaction type, etc.
        Integer exportId = record.getExport_id();
        FileFormat fileFormat = record.getFile_format();
        TransactionType transactionType = record.getTransaction_type();
        LocalDateTime startDatetime = record.getStart_datetime();
        LocalDateTime endDatetime = record.getEnd_datetime();

        

    }
}
