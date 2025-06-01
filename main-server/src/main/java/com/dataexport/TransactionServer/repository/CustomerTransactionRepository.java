package com.dataexport.TransactionServer.repository;

import com.dataexport.TransactionServer.model.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {
    List<CustomerTransaction> findByTransactionTimeBetween(LocalDateTime start, LocalDateTime end);
}
