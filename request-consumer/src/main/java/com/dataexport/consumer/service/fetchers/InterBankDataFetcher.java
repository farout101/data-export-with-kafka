package com.dataexport.consumer.service.fetchers;

import com.dataexport.consumer.model.InterbankTransaction;
import com.dataexport.consumer.repository.InterbankTransferRepository;
import com.dataexport.consumer.service.interfaces.DataFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterBankDataFetcher implements DataFetchService {

    private final InterbankTransferRepository interbankRepo;

    @Autowired
    public InterBankDataFetcher(InterbankTransferRepository interbankRepo) {
        this.interbankRepo = interbankRepo;
    }

    @Override
    public List<InterbankTransaction> fetchData(LocalDateTime start, LocalDateTime end) {
        return interbankRepo.findByTransactionTimeBetween(start, end);
    }
}
