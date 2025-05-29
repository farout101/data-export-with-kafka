package com.dataexport.consumer.service;

import com.dataexport.consumer.enu.FileFormat;
import com.dataexport.consumer.enu.TransactionType;
import com.dataexport.consumer.model.ResponseRecord;
import com.dataexport.consumer.service.fetchers.AtmDataFetcher;
import com.dataexport.consumer.service.fetchers.CustomerDataFetcher;
import com.dataexport.consumer.service.fetchers.InterBankDataFetcher;
import com.dataexport.consumer.model.RequestRecord;
import com.dataexport.consumer.service.fileExporter.CsvExporter;
import com.dataexport.consumer.service.fileExporter.JsonExporter;
import com.dataexport.consumer.service.fileExporter.XlsxExporter;
import com.dataexport.consumer.service.interfaces.DataFetchService;
import com.dataexport.consumer.service.interfaces.Exporter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageProcessorService {

    Logger log = LoggerFactory.getLogger(StorageProcessorService.class);

    private final AtmDataFetcher atmDataFetcher;
    private final CustomerDataFetcher customerDataFetcher;
    private final InterBankDataFetcher interBankDataFetcher;
    private final CsvExporter csvExporter;
    private final JsonExporter jsonExporter;
    private final XlsxExporter xlsxExporter;
    private final PrepareResponse prepareResponse;

    public void processRecord(RequestRecord record) {
        Integer exportId = record.getExport_id();
        FileFormat fileFormat = record.getFile_format();
        TransactionType transactionType = record.getTransaction_type();
        LocalDateTime startDatetime = record.getStart_datetime();
        LocalDateTime endDatetime = record.getEnd_datetime();

        log.info("Processing record: exportId={}, fileFormat={}, transactionType={}, startDatetime={}, endDatetime={}",
                exportId, fileFormat, transactionType, startDatetime, endDatetime);

        // 1. Decide which service to use
        DataFetchService fetchService = resolveFetchService(transactionType);

        // 2. Fetch data
        List<?> data = fetchService.fetchData(startDatetime, endDatetime);

        // 3. Export data
        Exporter exportService = resolveExportService(fileFormat);
        exportService.export(exportId, data);

        ResponseRecord responseRecord =  prepareResponse.prepare(
                exportId,
                "export_" + exportId + "." + fileFormat.name().toLowerCase(),
                fileFormat,
                "SUCCESS",
                "Data exported successfully",
                "/download/export_" + exportId + "." + fileFormat.name().toLowerCase()
        );
    }

    private Exporter resolveExportService(FileFormat fileFormat) {
        log.info("Resolving export service for file format: {}", fileFormat);
        return switch (fileFormat) {
            case CSV -> csvExporter; // Replace with actual service
            case JSON -> jsonExporter; // Replace with actual service
            case XLSX -> xlsxExporter; // Replace with actual service
        };
    }

    private DataFetchService resolveFetchService(TransactionType transactionType) {
        log.info("Resolving fetch service for transaction type: {}", transactionType);
        return switch (transactionType) {
            case ATM -> atmDataFetcher;
            case CUSTOMER -> customerDataFetcher;
            case INTER_BANK -> interBankDataFetcher; // Replace with actual service
        };
    }

}
