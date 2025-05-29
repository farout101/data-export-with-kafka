package com.dataexport.consumer.service.fetchers;

import com.dataexport.consumer.model.CustomerTransaction;
import com.dataexport.consumer.repository.CustomerTransactionRepository;
import com.dataexport.consumer.service.interfaces.DataFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerDataFetcher implements DataFetchService {

    private final CustomerTransactionRepository customerRepo;

    @Autowired
    public CustomerDataFetcher(CustomerTransactionRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public List<CustomerTransaction> fetchData(LocalDateTime start, LocalDateTime end) {
        return customerRepo.findByTransactionTimeBetween(start, end);
    }

}
