package com.dataexport.TransactionServer.service.fetchers;

import com.dataexport.TransactionServer.model.AtmTransaction;
import com.dataexport.TransactionServer.repository.AtmWithdrawalRepository;
import com.dataexport.TransactionServer.service.interfaces.DataFetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtmDataFetcher implements DataFetchService {

    Logger log = LoggerFactory.getLogger(AtmDataFetcher.class);

    private final AtmWithdrawalRepository atmRepo;

    @Autowired
    public AtmDataFetcher(AtmWithdrawalRepository atmRepo) {
        this.atmRepo = atmRepo;
        log.info("AtmDataFetcher initialized with repository: {}", atmRepo.getClass().getSimpleName());
    }

    @Override
    public List<AtmTransaction> fetchData(LocalDateTime start, LocalDateTime end) {
        log.info("Fetching ATM transactions between {} and {}", start, end);
        return atmRepo.findByTransactionTimeBetween(start, end);
    }
}
