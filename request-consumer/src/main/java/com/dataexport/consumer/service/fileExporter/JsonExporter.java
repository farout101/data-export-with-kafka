package com.dataexport.consumer.service.fileExporter;

import com.dataexport.consumer.service.interfaces.Exporter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonExporter implements Exporter {
    @Override
    public void export(Integer exportId, List<?> data) {

    }
}
