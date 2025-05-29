package com.dataexport.consumer.service.fetchers;

import com.dataexport.consumer.model.AtmTransaction;
import com.dataexport.consumer.repository.AtmWithdrawalRepository;
import com.dataexport.consumer.service.interfaces.DataFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtmDataFetcher implements DataFetchService {

    private final AtmWithdrawalRepository atmRepo;

    @Autowired
    public AtmDataFetcher(AtmWithdrawalRepository atmRepo) {
        this.atmRepo = atmRepo;
    }

    @Override
    public List<AtmTransaction> fetchData(LocalDateTime start, LocalDateTime end) {
        return atmRepo.findByTransactionTimeBetween(start, end);
    }
}
