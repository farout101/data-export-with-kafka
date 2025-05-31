package com.dataexport.TransactionServer.repository;

import com.dataexport.TransactionServer.model.AtmTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AtmWithdrawalRepository extends JpaRepository<AtmTransaction, Long> {
    List<AtmTransaction> findByTransactionTimeBetween(LocalDateTime start, LocalDateTime end);
}
