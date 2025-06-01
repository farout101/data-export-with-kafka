package com.dataexport.TransactionServer.service.interfaces;

import java.util.List;

public interface Exporter {
    void export(Integer exportId, List<?> data);
}