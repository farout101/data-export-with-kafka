package com.dataexport.TransactionServer.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;

public interface DataFetchService {
    List<?> fetchData(LocalDateTime start, LocalDateTime end);
}

