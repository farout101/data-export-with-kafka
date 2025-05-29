package com.dataexport.consumer.service.fileExporter;

import com.dataexport.consumer.service.interfaces.Exporter;
import com.dataexport.consumer.service.utality.FileExportUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.stream.Collectors;

@Service
public class CsvExporter implements Exporter {

    Logger log = LoggerFactory.getLogger(CsvExporter.class);

    @Override
    public void export(Integer exportId, List<?> data) {
        if (data == null || data.isEmpty()) return;

        Object first = data.get(0);
        Field[] fields = first.getClass().getDeclaredFields();

        File file = FileExportUtils.getOrCreateFile("CSVFiles", "export_" + exportId + ".csv");

        try (PrintWriter writer = new PrintWriter(file)) {
            // Write header
            String header = Arrays.stream(fields)
                    .map(Field::getName)
                    .collect(Collectors.joining(","));
            writer.println(header);

            // Write data
            for (Object obj : data) {
                String row = Arrays.stream(fields)
                        .map(f -> {
                            f.setAccessible(true);
                            try {
                                return String.valueOf(f.get(obj));
                            } catch (IllegalAccessException e) {
                                return "";
                            }
                        })
                        .collect(Collectors.joining(","));
                writer.println(row);
            }

            System.out.println("CSV exported to: " + file.getAbsolutePath());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
