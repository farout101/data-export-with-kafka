package com.dataexport.consumer.model;

import java.time.LocalDateTime;

public record RecordEvent(
    Integer export_id,
    FileFormat file_format,
    TransactionType transaction_type,
    LocalDateTime start_datetime,
    LocalDateTime end_datetime
) {
}
