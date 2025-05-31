package com.dataexport.TransactionServer.repository;

import com.dataexport.TransactionServer.model.InterbankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InterbankTransferRepository extends JpaRepository<InterbankTransaction, Long> {
    List<InterbankTransaction> findByTransactionTimeBetween(LocalDateTime start, LocalDateTime end);
}
