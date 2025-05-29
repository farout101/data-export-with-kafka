package com.dataexport.consumer.repository;

import com.dataexport.consumer.model.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Integer> {
}
