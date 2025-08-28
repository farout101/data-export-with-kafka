package com.dataexport.TransactionServer.service;

import com.dataexport.TransactionServer.enu.FileFormat;
import com.dataexport.TransactionServer.enu.TransactionType;
import com.dataexport.TransactionServer.record.ResponseRecord;
import com.dataexport.TransactionServer.service.fetchers.AtmDataFetcher;
import com.dataexport.TransactionServer.service.fetchers.CustomerDataFetcher;
import com.dataexport.TransactionServer.service.fetchers.InterBankDataFetcher;
import com.dataexport.TransactionServer.record.RequestRecord;
import com.dataexport.TransactionServer.service.fileExporter.CsvExporter;
import com.dataexport.TransactionServer.service.fileExporter.JsonExporter;
import com.dataexport.TransactionServer.service.fileExporter.XlsxExporter;
import com.dataexport.TransactionServer.service.interfaces.DataFetchService;
import com.dataexport.TransactionServer.service.interfaces.Exporter;
import com.dataexport.TransactionServer.service.kafka.ProducerService;
import com.dataexport.TransactionServer.service.utality.FileCleaner;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExportProcessorService {

    Logger log = LoggerFactory.getLogger(ExportProcessorService.class);

    private final AtmDataFetcher atmDataFetcher;
    private final CustomerDataFetcher customerDataFetcher;
    private final InterBankDataFetcher interBankDataFetcher;
    private final CsvExporter csvExporter;
    private final JsonExporter jsonExporter;
    private final XlsxExporter xlsxExporter;
    private final PrepareResponse prepareResponse;
    private final ProducerService producerService;
    private final FileCleaner fileCleaner;

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

        String baseDownloadUrl = "http://localhost:8090";
        String fileName = "/export_" + exportId + "." + fileFormat.name().toLowerCase();
        String fileNameWithType = fileFormat + "-files" + fileName;

        ResponseRecord responseRecord = prepareResponse.prepare(
                exportId,
                fileName,
                fileFormat,
                "SUCCESS",
                "Data exported successfully",
                baseDownloadUrl + "/download/"+ fileNameWithType
        );

        producerService.sendRequest(responseRecord);

        fileCleaner.scheduleFileDeletion(fileCleaner.getFilePath(fileNameWithType), 15);
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
