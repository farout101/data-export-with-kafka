package com.dataexport.consumer.repository;

import com.dataexport.consumer.model.InterbankTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterbankTransferRepository extends JpaRepository<InterbankTransfer, Long> {
}
