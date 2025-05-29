package com.dataexport.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterbankTransfer extends JpaRepository<InterbankTransfer, Integer> {
}
