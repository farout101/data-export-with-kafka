package com.dataexport.consumer.service.fetchers;

import com.dataexport.consumer.model.CustomerTransaction;
import com.dataexport.consumer.repository.CustomerTransactionRepository;
import com.dataexport.consumer.service.interfaces.DataFetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerDataFetcher implements DataFetchService {

    Logger log = LoggerFactory.getLogger(CustomerDataFetcher.class);

    private final CustomerTransactionRepository customerRepo;

    @Autowired
    public CustomerDataFetcher(CustomerTransactionRepository customerRepo) {
        this.customerRepo = customerRepo;
        log.info("CustomerDataFetcher initialized with repository: {}", customerRepo.getClass().getSimpleName());
    }

    @Override
    public List<CustomerTransaction> fetchData(LocalDateTime start, LocalDateTime end) {
        log.info("Fetching Customer transactions between {} and {}", start, end);
        return customerRepo.findByTransactionTimeBetween(start, end);
    }

}
