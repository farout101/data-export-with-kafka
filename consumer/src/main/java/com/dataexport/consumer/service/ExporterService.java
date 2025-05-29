package com.dataexport.consumer.service;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExporterService {

    static void writeJson(List<Map<String, Object>> data, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), data);
    }

    static void writeCsv(List<Map<String, Object>> data, String filename) throws IOException {
        if (data.isEmpty()) return;
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            List<String> headers = new ArrayList<>(data.get(0).keySet());
            writer.println(String.join(",", headers));

            for (Map<String, Object> row : data) {
                List<String> values = new ArrayList<>();
                for (String header : headers) {
                    values.add(String.valueOf(row.get(header)));
                }
                writer.println(String.join(",", values));
            }
        }
    }

    static void writeExcel(List<Map<String, Object>> data, String filename) throws IOException {
        if (data.isEmpty()) return;
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");
            Row headerRow = sheet.createRow(0);
            List<String> headers = new ArrayList<>(data.get(0).keySet());

            for (int i = 0; i < headers.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers.get(i));
            }

            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Map<String, Object> rowData = data.get(i);
                for (int j = 0; j < headers.size(); j++) {
                    Object value = rowData.get(headers.get(j));
                    row.createCell(j).setCellValue(value == null ? "" : value.toString());
                }
            }

            try (FileOutputStream out = new FileOutputStream(filename)) {
                workbook.write(out);
            }
        }
    }
}
