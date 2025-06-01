package com.dataexport.TransactionServer.service.fetchers;

import com.dataexport.TransactionServer.model.InterbankTransaction;
import com.dataexport.TransactionServer.repository.InterbankTransferRepository;
import com.dataexport.TransactionServer.service.interfaces.DataFetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterBankDataFetcher implements DataFetchService {

    Logger log = LoggerFactory.getLogger(InterBankDataFetcher.class);

    private final InterbankTransferRepository interbankRepo;

    @Autowired
    public InterBankDataFetcher(InterbankTransferRepository interbankRepo) {
        this.interbankRepo = interbankRepo;
        log.info("InterBankDataFetcher initialized with repository: {}", interbankRepo.getClass().getSimpleName());
    }

    @Override
    public List<InterbankTransaction> fetchData(LocalDateTime start, LocalDateTime end) {
        log.info("Fetching InterBank transactions between {} and {}", start, end);
        return interbankRepo.findByTransactionTimeBetween(start, end);
    }
}
