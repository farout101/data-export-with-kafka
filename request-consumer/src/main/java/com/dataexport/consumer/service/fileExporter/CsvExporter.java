package com.dataexport.consumer.service.fileExporter;

import com.dataexport.consumer.service.interfaces.Exporter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsvExporter implements Exporter {
    @Override
    public void export(Integer exportId, List<?> data) {
        // actual CSV export logic here
    }
}
