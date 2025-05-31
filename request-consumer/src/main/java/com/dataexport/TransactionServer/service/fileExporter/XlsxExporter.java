package com.dataexport.TransactionServer.service.fileExporter;

import com.dataexport.TransactionServer.service.interfaces.Exporter;
import com.dataexport.TransactionServer.service.utality.FileExportUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Service
public class XlsxExporter implements Exporter {

    Logger log = LoggerFactory.getLogger(XlsxExporter.class);

    @Override
    public void export(Integer exportId, List<?> data) {
        if (data == null || data.isEmpty()) return;

        File file = FileExportUtils.getOrCreateFile("xlsx-files", "export_" + exportId + ".xlsx");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Export");
            int rowIdx = 0;

            // Reflectively generate headers from first object's fields
            Object first = data.get(0);
            Field[] fields = first.getClass().getDeclaredFields();

            // Write header
            Row headerRow = sheet.createRow(rowIdx++);
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(fields[i].getName());
            }

            // Write data rows
            for (Object obj : data) {
                Row row = sheet.createRow(rowIdx++);
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    Object value = fields[i].get(obj);
                    Cell cell = row.createCell(i);
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }

            // Write to file
            try (FileOutputStream fos = new FileOutputStream(file)) {
                workbook.write(fos);
            }

            log.info("XLSX exported to: {}", file.getAbsolutePath());

        } catch (IllegalAccessException | IOException e) {
            log.error("Error exporting XLSX", e);
        }
    }
}

