package com.test.requestproducer.record;

import com.test.requestproducer.enu.FileFormat;
import com.test.requestproducer.enu.TransactionType;

import java.time.LocalDateTime;

public record RequestRecord(
        Integer export_id,
        FileFormat file_format,
        TransactionType transaction_type,
        LocalDateTime start_datetime,
        LocalDateTime end_datetime
) {}
