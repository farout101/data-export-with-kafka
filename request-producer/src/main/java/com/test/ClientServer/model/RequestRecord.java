package com.test.ClientServer.model;

import com.test.ClientServer.enu.FileFormat;
import com.test.ClientServer.enu.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRecord {
    private Integer export_id;
    private FileFormat file_format;
    private TransactionType transaction_type;
    private LocalDateTime start_datetime;
    private LocalDateTime end_datetime;
}
