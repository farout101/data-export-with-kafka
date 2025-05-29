package com.dataexport.consumer.record;

import com.dataexport.consumer.enu.FileFormat;
import com.dataexport.consumer.enu.TransactionType;

import java.time.LocalDateTime;

public record RequestRecord(
    Integer export_id,
    FileFormat file_format,
    TransactionType transaction_type,
    LocalDateTime start_datetime,
    LocalDateTime end_datetime
) {
}
