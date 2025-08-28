package com.dataexport.TransactionServer.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRecord {
    private Integer exportId;
    private String fileName;
    private String fileFormat;
    private String status;
    private String message;
    private String downloadUrl;
}
