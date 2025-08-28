package com.dataexport.TransactionServer.record;

import com.dataexport.TransactionServer.enu.FileFormat;
import com.dataexport.TransactionServer.enu.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRecord {
    private Integer export_id;
    private FileFormat file_format;
    private TransactionType transaction_type;
    private LocalDateTime start_datetime;
    private LocalDateTime end_datetime;
}
